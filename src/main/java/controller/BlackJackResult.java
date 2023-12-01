package controller;

import static global.constants.BLACKJACK_GAIN;
import static global.constants.BLACK_NUMBER;
import static global.constants.DEALER;

import domain.Calculation;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import view.OutputView;

public class BlackJackResult {
    private OutputView outputView;
    private Calculation calculation;
    public BlackJackResult(OutputView outputView){
        this.outputView = outputView;
        this.calculation = new Calculation();
    }
    public void run(Players players, Dealer dealer){
        calculatorProfit(players, dealer);
        userCards(players, dealer);
        userProfit(players, dealer);
    }

    private void calculatorProfit(Players players, Dealer dealer) {
        players.getPlayers().forEach(onePlayer -> {
            int check = calculation.calculation(onePlayer, dealer);
            if(check == 2){
                playerWin(onePlayer, dealer);
                dealerWin(onePlayer, dealer);
            }
            else if(check == 1){
                playerWinGain(onePlayer, dealer);
            }
            else if(check == -1){
                dealerWinGain(onePlayer, dealer);
            }
        });
    }

    private void playerWin(Player onePlayer, Dealer dealer) {
        if((onePlayer.calculationCard() > dealer.calculationCard() && onePlayer.calculationCard() <= BLACK_NUMBER)
                || dealer.calculationCard() > BLACK_NUMBER){
            playerWinGain(onePlayer, dealer);
        }
    }

    private void dealerWin(Player onePlayer, Dealer dealer) {
        if((onePlayer.calculationCard() < dealer.calculationCard() && dealer.calculationCard() <= BLACK_NUMBER)
                || onePlayer.calculationCard() > BLACK_NUMBER){
            dealerWinGain(onePlayer, dealer);
        }
    }

    private void playerWinGain(Player onePlayer, Dealer dealer) {
        double playerProfit = onePlayer.getBlackJack() ? onePlayer.getBettingMoney() * BLACKJACK_GAIN : onePlayer.getBettingMoney();
        onePlayer.setProfit(playerProfit);
        dealer.setProfit(-playerProfit);
    }

    private void dealerWinGain(Player onePlayer, Dealer dealer) {
        double playerProfit = onePlayer.getBettingMoney();
        onePlayer.setProfit(-playerProfit);
        dealer.setProfit(playerProfit);
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
