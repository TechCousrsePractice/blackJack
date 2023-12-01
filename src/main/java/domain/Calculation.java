package domain;

import static global.constants.BLACK_NUMBER;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;
import java.util.List;
import java.util.stream.IntStream;

public class Calculation {
    public int calculation(Player player, Dealer dealer) {
        int max = Math.min(player.getCards().size(), dealer.getCards().size());
        int playerSum = playerSum(max, player.getCards());
        int dealerSum = dealerSum(max, dealer.getCards());

        if (playerSum > BLACK_NUMBER && dealerSum > BLACK_NUMBER) {
            return 0;
        } else if (dealerSum > BLACK_NUMBER) {
            return 1;
        } else if (playerSum > BLACK_NUMBER) {
            return -1;
        }
        return 2;
    }

    private int playerSum(int max, List<Card> playerCards) {
        return IntStream.range(0, max)
                .map(index -> playerCards.get(index).getNumber())
                .sum();
    }

    private int dealerSum(int max, List<Card> dealerCards){
        return IntStream.range(0, max)
                .map(index -> dealerCards.get(index).getNumber())
                .sum();
    }
}
