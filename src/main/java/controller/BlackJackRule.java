package controller;

import static global.constants.BLACK_NUMBER;
import static global.constants.DEALER_NUMBER;
import static global.constants.NO;

import domain.card.Cards;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import java.util.Objects;
import view.InputView;
import view.OutputView;

public class BlackJackRule {
    private final InputView inputView;
    private final OutputView outputView;
    private final Cards cards;

    public BlackJackRule(InputView inputView, OutputView outputView, Cards cards) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.cards = cards;
    }

    public void run(Players players, Dealer dealer) {
        isBlackJackDealer(dealer);
        players.getPlayers().forEach(this::isBlackJackPlayer);
        players.getPlayers().forEach(onePlayer -> {
            if(!onePlayer.getBlackJack()){
                onePlayerGame(onePlayer);
            }
        });
        dealerGame(dealer);
    }

    private void isBlackJackDealer(Dealer dealer) {
        if(dealer.calculationCard() == BLACK_NUMBER) {
            dealer.setHaveFirstBlackJack();
        }
    }

    private void isBlackJackPlayer(Player player){
        if(player.calculationCard() == BLACK_NUMBER) {
            player.setHaveFirstBlackJack();
        }
    }

    private void onePlayerGame(Player player) {
        while (true) {
            if (Objects.equals(inputView.enterAddCard(player.getName()), NO)) {
                return;
            }
            player.addCard(cards.getCard());
            outputView.onePlayerCards(player.getName(), player.getCards());
            if(player.calculationCard() >= BLACK_NUMBER) {
                return;
            }
        }
    }

    private void dealerGame(Dealer dealer) {
        while (true) {
            if (dealer.calculationCard() > DEALER_NUMBER){
                return;
            }
            dealer.addCard(cards.getCard());
            outputView.dealerGetCard();
        }
    }
}
