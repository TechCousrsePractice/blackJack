package domain.user;

import domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private static final int MINIMUM_BETTING_MONEY_VALUE = 1;
    private final String name;
    private final double bettingMoney;
    private final List<Card> cards = new ArrayList<>();

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public static Player of(String name, int bettingMoney) {
        validate(bettingMoney);
        return new Player(name, bettingMoney);
    }

    private static void validate(int bettingMoney) {
        if (bettingMoney < MINIMUM_BETTING_MONEY_VALUE) {
            throw new IllegalArgumentException("베팅금액이 0이하 입니다.");
        }
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    // TODO function

}
