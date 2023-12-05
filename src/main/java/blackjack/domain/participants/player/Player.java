package blackjack.domain.participants.player;

import blackjack.domain.card.Cards;
import blackjack.domain.participants.Participant;

public class Player extends Participant {
    private final String name;
    private final long bettingAmount;

    private Player(String name, int bettingAmount) {
        super();
        this.name = name;
        this.bettingAmount = bettingAmount;
    }

    public static Player from(String name, int bettingAmount) {
        return new Player(name, bettingAmount);
    }

    public boolean isInitialBlackjack() {
        return isBlackjack() && getCards().isInitialSize();
    }

    public String getName() {
        return name;
    }

    public long getBettingAmount() {
        return bettingAmount;
    }
}
