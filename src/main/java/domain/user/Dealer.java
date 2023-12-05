package domain.user;

import static domain.constant.GameConstant.DEALER_SUM_OF_CARD_THRESHOLD;

import domain.card.Card;
import domain.util.ScoreCalculator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
    private final List<Card> cards = new ArrayList<>();

    public Dealer() {
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean sumOfCardsUnderSixteen() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum() <= DEALER_SUM_OF_CARD_THRESHOLD;
    }

    public int produceScore() {
        return ScoreCalculator.calculateScore(cards);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}