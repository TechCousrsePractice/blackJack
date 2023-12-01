package domain.user;

import domain.card.Card;
import domain.card.Symbol;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dealer implements User {
    private final List<Card> cards = new ArrayList<>();

    public Dealer() {
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Card getCard() {
        return cards.get(0);
    }

    @Override
    public List<Card> getCurrentCards() {
        return Collections.unmodifiableList(cards);
    }

    @Override
    public int getScore() {
        List<Integer> scores = getScores();

        if (scores.size() == 0) {
            return 22;
        }

        return scores.stream()
                .max(Integer::compareTo)
                .get();
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

    public boolean canGetAdditionalCard() {
        List<Integer> scores = getScores();

        return scores.stream()
                .filter(score -> score <= 16)
                .count() > 0;
    }
}
