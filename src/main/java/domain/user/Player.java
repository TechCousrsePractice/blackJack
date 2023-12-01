package domain.user;

import domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private static final int INITIAL_BETTING_MONEY = 0;
    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public static Player from(String name) {
        return new Player(name, INITIAL_BETTING_MONEY);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    // TODO function

}
