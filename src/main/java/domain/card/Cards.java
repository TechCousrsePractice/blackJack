package domain.card;

import static camp.nextstep.edu.missionutils.Randoms.pickNumberInRange;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Cards {
    private final Map<Integer, Card> cards = new HashMap<>();
    private Cards() {
        createCards(CardFactory.create());
    }

    public static Cards create() {
        return new Cards();
    }

    public Card getCard() {
        Card giveCard = null;
        do {
            int giveCardNumber = pickNumberInRange(0, 51);
            if (cards.get(giveCardNumber) != null) {
                giveCard = cards.get(giveCardNumber);
                cards.remove(giveCardNumber);
            }
        } while (giveCard == null);
        return giveCard;
    }

    private void createCards(List<Card> createCard) {
        AtomicInteger cardNumber = new AtomicInteger(0);

        createCard.forEach(oneCard -> {
            this.cards.put(cardNumber.getAndIncrement(), oneCard);
        });
    }
}
