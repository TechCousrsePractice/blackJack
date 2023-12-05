package blackjack.domain.participants;

import blackjack.domain.card.Cards;

public class Dealer extends Participant {
    public static Dealer from(Cards cards) {
        Dealer dealer = new Dealer();
        dealer.initializeCards(cards);
        return dealer;
    }
}
