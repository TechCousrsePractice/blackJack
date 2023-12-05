package dto;

import java.util.List;

public record DealerCardsAndScoreDto(List<String> cardNames, int score) {
    public static DealerCardsAndScoreDto of(List<String> cardNames, final int score) {
        return new DealerCardsAndScoreDto(cardNames, score);
    }

    public int getCardSize() {
        return cardNames.size();
    }
}