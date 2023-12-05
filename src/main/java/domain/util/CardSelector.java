package domain.util;

import domain.card.Card;
import domain.card.CardFactory;
import java.util.List;

public class CardSelector {
    private static List<Card> CARDS = CardFactory.create();

    public static Card selectCard() {
        int cardIndex = RandomNumberGenerator.generateByLimitConstraint(CARDS.size() - 1);
        fillCardsIfEmpty();
        return CARDS.remove(cardIndex);
    }

    private static void fillCardsIfEmpty() {
        if (CARDS.isEmpty()) {
            CARDS = CardFactory.create();
        }
    }
}