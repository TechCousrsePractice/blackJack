package domain.user;

import domain.card.Card;
import domain.card.Symbol;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player implements User {
    private static final int MINIMUM_BETTING_MONEY_VALUE = 1;
    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public static Player of(String name, int bettingMoney) {
        validate(bettingMoney);
        return new Player(name, bettingMoney);
    }

    private static void validate(int bettingMoney) {
        if (bettingMoney < MINIMUM_BETTING_MONEY_VALUE) {
            throw new IllegalArgumentException("베팅금액이 0이하 입니다.");
        }
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public List<Card> getCurrentCards() {
        return Collections.unmodifiableList(cards);
    }

    @Override
    public int getScore() {
        List<Integer> scores = getScores();

        if (scores.isEmpty()) {
            return 22;
        }

        return scores.stream()
                .max(Integer::compareTo)
                .get();
    }

    @Override
    public boolean isExplode() {
        return getScores().isEmpty();
    }

    @Override
    public boolean isBlackjack() {
        return cards.size() == 2 && getScores().contains(21);
    }

    private List<Integer> getScores() {
        List<Integer> scores = List.of(0);
        for (Card card : cards) {
            List<Integer> addScore = getAddScore(card);
            scores = scores.stream()
                    .flatMap(score -> addScore.stream().map(adder -> score + adder))
                    .toList();
        }

        return scores.stream()
                .filter(score -> score <= 21)
                .toList();
    }

    private List<Integer> getAddScore(Card card) {
        List<Integer> addScore = new ArrayList<>();
        addScore.add(card.getScore());
        if (card.isEqualSymbol(Symbol.ACE)) {
            addScore.add(11);
        }

        return addScore;
    }

    public String getName() {
        return name;
    }

    public double getBettingMoney() {
        return bettingMoney;
    }

    // TODO function

}
