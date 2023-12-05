package domain.user;

import static domain.constant.GameConstant.SUM_OF_CARD_THRESHOLD;

import domain.card.Card;
import domain.util.ScoreCalculator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player {
    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    private Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public static Player of(String name, final double bettingMoney) {
        return new Player(name, bettingMoney);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean cardsExceedsThreshold() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum() >= SUM_OF_CARD_THRESHOLD;
    }

    public int produceScore() {
        return ScoreCalculator.calculateScore(cards);
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}