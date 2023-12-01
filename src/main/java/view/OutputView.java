package view;

import static view.constants.OutputConstants.CARD_SEPARATOR;
import static view.constants.OutputConstants.DEALER_GET_CARD;
import static view.constants.OutputConstants.INIT_CARD;
import static view.constants.OutputConstants.INIT_LAST_PROFIT;
import static view.constants.OutputConstants.ONE_PLAYER_CARDS;
import static view.constants.OutputConstants.ONE_PLAYER_PROFIT;
import static view.constants.OutputConstants.RESULT_CARD;
import static view.constants.OutputConstants.RESULT_CARD_NUMBER;

import domain.card.Card;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private OutputView(){}

    public static OutputView create() {
        return new OutputView();
    }

    public void initCard(List<String> playerNames) {
        System.out.printf(INIT_CARD, String.join(CARD_SEPARATOR, playerNames));
    }
    public void onePlayerCards(String name, List<Card> cards) {
        System.out.printf(ONE_PLAYER_CARDS, name, cards.stream()
                .map(oneCard -> oneCard.requestSymbol() + oneCard.requestType())
                .collect(Collectors.joining(CARD_SEPARATOR)));
    }

    public void dealerGetCard() {
        System.out.println(DEALER_GET_CARD);
    }

    public void oneResultCard(String name, List<Card> cards, int result) {
        System.out.printf(RESULT_CARD, name, cards.stream()
                .map(oneCard -> oneCard.requestSymbol() + oneCard.requestType())
                .collect(Collectors.joining(CARD_SEPARATOR)));
        System.out.printf(RESULT_CARD_NUMBER, result);
    }

    public void lastInit() {
        System.out.println(INIT_LAST_PROFIT);
    }

    public void onePlayerProfit(String name, double profit) {
        System.out.printf(ONE_PLAYER_PROFIT, name, (int)profit);
    }
}
