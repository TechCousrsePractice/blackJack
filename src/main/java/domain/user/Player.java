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

    public int getCardSum() {
        return cards.stream()
                .mapToInt(Card::getCardNumber)
                .sum();
    }

    public String getName() {
        return name;
    }

    // betting money를 조작하기 위해 get을 사용 안하고
    // win, loose, draw method 제작을 하고싶으나 final일 경우 어떻게 하면 좋은지
    public double win() {
        return bettingMoney * 1.5;
    }

    public double lose() {
        return 0;
    }

    public double draw() {
        return bettingMoney;
    }
}
