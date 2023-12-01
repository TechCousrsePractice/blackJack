import domain.card.Trump;
import domain.user.Player;
import dto.PlayersDto;
import java.util.List;
import view.InputView;

public class BlackjackApplictaion {
    private final Trump trump;
    private final InputView inputView = new InputView();

    public BlackjackApplictaion(Trump trump) {
        this.trump = trump;
    }

    public void start() {
        PlayersDto requestPlayers = inputView.requestPlayerName();
        List<Player> players = requestPlayers.names().stream()
                .map(Player::from)
                .toList();
    }
}
