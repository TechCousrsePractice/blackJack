package dto;

import java.util.List;

public record PlayerCardsAndScoreDto(String playerName, List<String> cardNames, int score) {
    public static PlayerCardsAndScoreDto of(String playerName, List<String> cardNames, final int score) {
        return new PlayerCardsAndScoreDto(playerName, cardNames, score);
    }

    public int getCardSize() {
        return cardNames.size();
    }
}