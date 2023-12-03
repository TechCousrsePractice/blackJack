package blackjack.domain.card;

import blackjack.domain.Constants;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private List<Card> cards;

    private Cards(List<Card> cards) {
        this.cards = cards;
    }

    public static Cards of(Card... cards) {
        return new Cards(new ArrayList<>(List.of(cards)));
    }

    public void add(Card card) {
        this.cards.add(card);
    }

    public boolean isBlackjack() {
        return calculateTotalScore() == Constants.BLACK_JACK_NUMBER;
    }

    public boolean exceedsBlackjack() {
        return calculateTotalScore() > Constants.BLACK_JACK_NUMBER;
    }

    public int calculateTotalScore() {
        int totalScore = calculateOriginalTotalScore();
        long aceCount = getAceCount();

        for (int i = 0; i < aceCount; i++) {
            if (totalScore > Constants.BLACK_JACK_NUMBER) {
                totalScore -= Constants.ACE_VALUE_DIFFERENCE;
            }
        }
        return totalScore;
    }

    private int calculateOriginalTotalScore() {
        return cards.stream()
                .mapToInt(Card::getValueNumber)
                .sum();
    }

    private long getAceCount() {
        return cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean isInitialSize() {
        return cards.size() == Constants.INITIAL_CARD_SIZE;
    }
}
