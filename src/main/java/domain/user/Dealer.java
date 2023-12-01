package domain.user;

import static domain.constant.GameConstant.DEALER_SUM_OF_CARD_THRESHOLD;

import domain.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
    private final List<Card> cards = new ArrayList<>();

    private Dealer() {
    }

    public static Dealer create() {
        return new Dealer();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean sumOfCardsUnderSixteen() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum() <= DEALER_SUM_OF_CARD_THRESHOLD;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}