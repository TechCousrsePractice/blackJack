package domain.user;

import domain.card.Card;
import java.util.List;

public interface User {
    void addCard(Card card);

    List<Card> getCurrentCards();

    int getScore();

    boolean isExplode();

    boolean isBlackjack();
}
