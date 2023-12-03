package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static blackjack.exception.ErrorMessage.OUT_OF_INDEX_CARD_DECK;

public class CardDeck {
    private List<Card> cards;

    private CardDeck() {
        this.cards = new ArrayList<>();
        for (Shape shape : Shape.values()) {
            for (Value value : Value.values()) {
                this.cards.add(Card.of(value, shape));
            }
        }

        Collections.shuffle(this.cards);
    }

    public static CardDeck create() {
        return new CardDeck();
    }

    public Cards initialDraw() {
        Card card1 = drawCard();
        Card card2 = drawCard();
        return Cards.of(card1, card2);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException(OUT_OF_INDEX_CARD_DECK.getMessage());
        }
        return cards.remove(cards.size() - 1);
    }
}
