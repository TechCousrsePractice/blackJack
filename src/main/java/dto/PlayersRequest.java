package dto;

import java.util.List;
import java.util.Map;

public record PlayersRequest(Map<String, Integer> playerNames) {
    public static PlayersRequest create(Map<String, Integer> playerNames) {
        return new PlayersRequest(playerNames);
    }
}
