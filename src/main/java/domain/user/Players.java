package domain.user;

import domain.util.Constant;
import java.util.List;

public class Players {
    private List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public String getPlayerNames() {
        return String.join(Constant.SEPARATOR, players.stream()
                .map(Player::getName)
                .toList());
    }
}
