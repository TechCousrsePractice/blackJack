package controller;

import static global.constants.DEALER;

import domain.card.Cards;
import domain.user.Dealer;
import domain.user.Players;
import dto.PlayersRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

public class BlackJack {
    private final InputView inputView;
    private final OutputView outputView;
    private static BlackJack instance;

    private BlackJack(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public static BlackJack getInstance(InputView inputView, OutputView outputView) {
        if (instance == null) {
            instance = new BlackJack(inputView, outputView);
        }
        return instance;
    }

    public void run() {
        Players players = initGame();
        Cards cards = Cards.create();
        Dealer dealer = createDealer(players, cards);

        runGame(players, dealer, cards);
        resultGame(players, dealer);
    }

    private Players initGame() {
        BlackJackInit blackJackInit = new BlackJackInit(inputView, outputView);
        return blackJackInit.run();
    }

    private Dealer createDealer(Players players, Cards cards) {
        players.firstGetCard(cards);
        Dealer dealer = new Dealer();
        dealer.addCard(cards.getCard());
        dealer.addCard(cards.getCard());
        outputView.onePlayerCards(DEALER, dealer.getCards());
        players.getPlayers().forEach(onePlayer -> outputView.onePlayerCards(onePlayer.getName(), onePlayer.getCards()));
        return dealer;
    }

    private void runGame(Players players, Dealer dealer, Cards cards) {
        BlackJackRule blackJackRule = new BlackJackRule(inputView, outputView, cards);
        blackJackRule.run(players, dealer);
    }

    private void resultGame(Players players, Dealer dealer) {
        BlackJackResult blackJackResult = new BlackJackResult(outputView);
        blackJackResult.run(players, dealer);
    }
}
