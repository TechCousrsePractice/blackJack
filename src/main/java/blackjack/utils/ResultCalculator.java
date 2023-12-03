package blackjack.utils;

import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.player.Player;
import blackjack.domain.participants.player.Players;
import blackjack.dto.PlayerProfitDto;
import blackjack.dto.ProfitDto;

import java.util.List;

public class ResultCalculator {
    private static final double INITIAL_BLACKJACK_PROFIT_RATE = 1.5;
    private static final double WINNING_RATE = 2;

    public static ProfitDto calculateProfit(Dealer dealer, Players players) {
        double sumOfPlayerReturns = players.getPlayers().stream()
                .mapToDouble(player -> calculateReturns(dealer, player))
                .sum();
        double dealerProfit = players.getTotalBet() - sumOfPlayerReturns;
        List<PlayerProfitDto> playerProfits = players.getPlayers().stream()
                .map(player -> new PlayerProfitDto(player.getName(), calculateReturns(dealer, player) - player.getBettingAmount()))
                .toList();
        return new ProfitDto(dealerProfit, playerProfits);
    }

    private static double calculateReturns(Dealer dealer, Player player) {
        if (player.isInitialBlackjack()) {
            return player.getBettingAmount() * INITIAL_BLACKJACK_PROFIT_RATE;
        }
        if (player.exceedsBlackjack()) {
            return 0;
        }
        if (dealer.exceedsBlackjack() || player.isHigherScore(dealer)) {
            return player.getBettingAmount() * WINNING_RATE;
        }
        if (dealer.isEqualScore(player)) {
            return player.getBettingAmount();
        }
        return 0;
    }
}
