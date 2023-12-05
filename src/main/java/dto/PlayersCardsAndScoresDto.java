package dto;

import java.util.List;

public record PlayersCardsAndScoresDto(List<PlayerCardsAndScoreDto> playerCardsAndScoreDtos) {
    public static PlayersCardsAndScoresDto from(List<PlayerCardsAndScoreDto> playerCardsAndScoreDtos) {
        return new PlayersCardsAndScoresDto(playerCardsAndScoreDtos);
    }
}
