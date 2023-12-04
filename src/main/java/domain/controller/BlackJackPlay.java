package domain.controller;

import domain.card.Card;
import domain.card.CardFactory;
import domain.model.Result;
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
    private final static int DRAWING_CARD_BASED = 16;
    private final static int BLACK_JACK = 21;
    private final static String DEALER_NAME = "딜러";
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
        pickCard(dealer, players, deck);
        pickCard(dealer, deck);
        printCardResult(dealer, players);
        getResult(dealer, players.getPlayers());
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

    private void pickCard(Dealer dealer, Players players, List<Card> deck) {
        players.getPlayers().forEach(i -> {
            while (i.getCardSum() <= BLACK_JACK && inputView.reDrawCard(i)) {
                dealer.distributeCardToPlayer(deck, i);
                outputView.printPlayerCard(i);
                System.out.println();
            }
        });
    }

    private void pickCard(Dealer dealer, List<Card> deck) {
        if (dealer.getCardSum() <= DRAWING_CARD_BASED) {
            outputView.printDealerDraw();
            dealer.addCard(deck);
        }
    }

    private void printCardResult(Dealer dealer, Players players) {
        outputView.printDealerCardResult(dealer);
        players.getPlayers()
                .forEach(outputView::printPlayerCardResult);
    }

    private void getResult(Dealer dealer, List<Player> players) {
        List<Double> playerProfits = new ArrayList<>();
        int dealerCardSum = dealer.getCardSum();
        int playerCardSum;
        double dealerProfit = 0;

        for (Player player : players) {
            double playerProfit = 0;
            playerCardSum = player.getCardSum();

            if (dealerCardSum > playerCardSum) {
                dealerProfit += player.draw();
                playerProfit = player.lose();
            } else if (dealerCardSum < playerCardSum) {
                dealerProfit -= player.win();
                playerProfit += player.win();
            } else if (dealerCardSum == playerCardSum) {
                playerProfit = player.draw();
            }
            playerProfits.add(playerProfit);
        }
        Result result = new Result(dealerProfit, playerProfits);
        printResult(result, players);
    }

    private void printResult(Result result, List<Player> players) {
        System.out.println();
        outputView.printProfit();
        outputView.printProfitResult(DEALER_NAME, (int) result.dealerProfit());

        for (int i = 0; i < players.size(); i++) {
            outputView.printProfitResult(players.get(i).getName(), (int) Math.round(result.playersProfit().get(i)));
        }
    }

}
