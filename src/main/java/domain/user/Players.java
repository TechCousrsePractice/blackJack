package domain.user;

import domain.card.Cards;
import dto.PlayersRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Players {
    private final List<Player> players = new ArrayList<>();

    private Players(Map<String, Integer> playerNames) {
        createPlayers(playerNames);
    }

    public static Players from(PlayersRequest playersRequest){
        return new Players(playersRequest.playerNames());
    }

    public void firstGetCard(Cards cards) {
        players.forEach(onePlayer -> onePlayer.addCard(cards.getCard()));
        players.forEach(onePlayer -> onePlayer.addCard(cards.getCard()));
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    private void createPlayers(Map<String, Integer> playerNames) {
        playerNames.forEach((oneName, bettingMoney) -> {
            Player player = new Player(oneName, bettingMoney);
            this.players.add(player);
        });
    }
}
