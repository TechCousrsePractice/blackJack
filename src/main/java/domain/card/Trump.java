package domain.card;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Trump {
    private final Map<Card, Boolean> trump;

    public Trump(Map<Card, Boolean> trump) {
        this.trump = trump;
    }

    public Card getRandomCard() {
        List<Card> unusedCards = trump.entrySet().stream()
                .filter(card -> !card.getValue())
                .map(Entry::getKey)
                .toList();

        return Randoms.shuffle(unusedCards).get(0);
    }
}
