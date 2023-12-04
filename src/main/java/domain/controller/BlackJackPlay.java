package domain.controller;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import domain.view.InputView;
import domain.view.OutputView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class BlackJackPlay {
    private final static int START_INDEX = 0;
    private final static InputView inputView = new InputView();
    private final static OutputView outputView = new OutputView();

    public void start() {
        Dealer dealer = new Dealer();
        Players players = readPlayersInfo();
        playBlackJack(dealer, players);
    }

    private Players readPlayersInfo() {
        List<String> playerNames = inputView.readPlayers();
        List<Double> playerBettingMoney = playerNames.stream()
                .map(inputView::readMoney)
                .toList();

        return registerPlayers(playerNames, playerBettingMoney);
    }

    private Players registerPlayers(List<String> playerNames, List<Double> playerBettingMoney) {
        List<Player> players = new ArrayList<>();

        IntStream.range(START_INDEX, playerNames.size()).forEach(i ->
                players.add(new Player(playerNames.get(i), playerBettingMoney.get(i)))
        );

        return new Players(players);
    }

    private void playBlackJack(Dealer dealer, Players players) {
        List<Card> deck = setTable(dealer, players);

    }

    private List<Card> setTable(Dealer dealer, Players players) {
        // Make Deck
        List<Card> deck = new ArrayList<>(CardFactory.create());
        Collections.shuffle(deck);

        dealer.distributeCardToPlayer(deck, players);
        dealer.addCard(deck);
        dealer.distributeCardToPlayer(deck, players);
        printTableInfo(dealer, players);

        return deck;
    }

    private void printTableInfo(Dealer dealer, Players players) {
        outputView.printSetTable(players.getPlayerNames());
        outputView.printDealerCard(dealer);
        players.getPlayers()
                .forEach(outputView::printPlayerCard);
    }


}
