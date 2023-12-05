package domain.card;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CardFactoryTest {
    @Test
    void create() {
//        List<Card> cards = CardFactory.create();
//        assertThat(cards).hasSize(52);
//        System.out.println(cards);
        CardDeck cardDeck = CardDeck.create();
        List<Card> cards = cardDeck.getCards();
        assertThat(cards).hasSize(52);
        System.out.println(cards);
    }
}
