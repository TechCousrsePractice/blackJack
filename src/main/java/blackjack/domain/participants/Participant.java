package blackjack.domain.participants;

import blackjack.domain.Constants;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Participant {
    private Cards cards;

    protected Participant() {
    }

    public void initializeCards(Cards cards) {
        this.cards = cards;
    }

    public boolean isBlackjack() {
        return cards.isBlackjack();
    }

    public boolean exceedsBlackjack() {
        return cards.exceedsBlackjack();
    }

    public boolean needsDraw() {
        return getTotalScore() <= Constants.DEALER_DRAW_THRESHOLD;
    }

    public int getTotalScore() {
        return cards.calculateTotalScore();
    }

    public boolean isEqualScore(Participant participant) {
        return getTotalScore() == participant.getTotalScore();
    }

    public boolean isHigherScore(Participant participant) {
        return getTotalScore() > participant.getTotalScore();
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public Cards getCards() {
        return cards;
    }
}
