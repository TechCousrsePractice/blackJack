package domain.user;

import domain.card.Card;
import domain.util.Constant;
import java.util.ArrayList;
import java.util.List;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player {
    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public String getCurrentlyCard() {
        return String.join(Constant.SEPARATOR, cards.stream()
                .map(Card::getCardName)
                .toList());
    }

    public String getName() {
        return name;
    }

}
