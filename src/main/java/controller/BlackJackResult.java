package controller;

import static global.constants.BLACKJACK_GAIN;
import static global.constants.BLACK_NUMBER;
import static global.constants.DEALER;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import view.OutputView;

public class BlackJackResult {
    private OutputView outputView;
    public BlackJackResult(OutputView outputView){
        this.outputView = outputView;
    }
    public void run(Players players, Dealer dealer){
        calculatorProfit(players, dealer);
        userCards(players, dealer);
        userProfit(players, dealer);
    }

    private void calculatorProfit(Players players, Dealer dealer) {
        players.getPlayers().forEach(onePlayer -> {
            playerWin(onePlayer, dealer);
            dealerWin(onePlayer, dealer);
        });
    }

    private void playerWin(Player onePlayer, Dealer dealer) {
        if((onePlayer.calculationCard() > dealer.calculationCard() && onePlayer.calculationCard() <= BLACK_NUMBER)
                || dealer.calculationCard() > BLACK_NUMBER){
            double playerProfit = onePlayer.getBlackJack() ? onePlayer.getBettingMoney() * BLACKJACK_GAIN : onePlayer.getBettingMoney();
            onePlayer.setProfit(playerProfit);
            dealer.setProfit(-playerProfit);
        }
    }

    private void dealerWin(Player onePlayer, Dealer dealer) {
        if((onePlayer.calculationCard() < dealer.calculationCard() && dealer.calculationCard() <= BLACK_NUMBER)
                || onePlayer.calculationCard() > BLACK_NUMBER){
            double playerProfit = onePlayer.getBettingMoney();
            onePlayer.setProfit(-playerProfit);
            dealer.setProfit(playerProfit);
        }
    }

    private void userCards(Players players, Dealer dealer) {
        outputView.oneResultCard(DEALER, dealer.getCards(), dealer.calculationCard());
        players.getPlayers().forEach(onePlayer -> {
            outputView.oneResultCard(onePlayer.getName(), onePlayer.getCards(), onePlayer.calculationCard());
        });
    }

    private void userProfit(Players players, Dealer dealer) {
        outputView.lastInit();
        outputView.onePlayerProfit(DEALER, dealer.getProfit());
        players.getPlayers().forEach(onePlayer -> {
            outputView.onePlayerProfit(onePlayer.getName(), onePlayer.getProfit());
        });
    }
}
