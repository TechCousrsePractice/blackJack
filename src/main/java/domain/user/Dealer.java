package domain.user;

import domain.card.Card;
import domain.util.Constant;
import java.util.ArrayList;
import java.util.List;

/**
 * 게임 딜러를 의미하는 객체
 */
public class Dealer {
    private final List<Card> cards = new ArrayList<>();

    public Dealer() {
    }

    public void addCard(List<Card> cards) {
        this.cards.add(pickCardInDeck(cards));
    }

    public void distributeCardToPlayer(List<Card> cards, Players players) {
        players.getPlayers()
                .forEach(i -> i.addCard(pickCardInDeck(cards)));
    }

    private Card pickCardInDeck(List<Card> cards) {
        return cards.get(Constant.CARD_INDEX++);
    }

    public String getCurrentlyCards() {
        return String.join(Constant.SEPARATOR, cards.stream()
                .map(Card::getCardName)
                .toList());
    }
}
