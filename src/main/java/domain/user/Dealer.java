package domain.user;

import domain.card.Card;
import java.util.ArrayList;
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
        return null;
    }

    @Override
    public int getScore() {
        return cards.stream()
                .map(Card::getScore)
                .reduce(0, Integer::sum);
    }

    @Override
    public int getTotalScore() {
        return 0;
    }
}
