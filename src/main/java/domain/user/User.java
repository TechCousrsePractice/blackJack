package domain.user;

import static global.constants.ACE_UNIT;
import static global.constants.BLACK_NUMBER;

import domain.card.Card;
import domain.card.Symbol;
import java.util.ArrayList;
import java.util.List;

public class User {
    private final List<Card> cards = new ArrayList<>();
    private boolean haveFirstBlackJack = false;
    private double profit = 0;

    public List<Card> getCards() {
        return this.cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void setHaveFirstBlackJack() {
        this.haveFirstBlackJack = true;
    }

    public boolean getBlackJack() {
        return this.haveFirstBlackJack;
    }

    public boolean isHaveACE() {
        return this.cards.stream()
                .anyMatch(card -> card.getSymbol() == Symbol.ACE);
    }

    public int calculationCard() {
        int sum = this.cards.stream()
                .mapToInt(Card::getNumber)
                .sum();
        if(isHaveACE() && sum + ACE_UNIT <= BLACK_NUMBER){
            return sum + ACE_UNIT;
        }
        return sum;
    }

    public double getProfit() {
        return this.profit;
    }

    public void setProfit(double profit) {
        this.profit = this.profit + profit;
    }
}
