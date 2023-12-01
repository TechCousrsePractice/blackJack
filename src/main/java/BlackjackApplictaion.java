import domain.card.Trump;
import domain.user.Player;
import dto.NamesDto;
import java.util.List;
import view.InputView;

public class BlackjackApplictaion {
    private final Trump trump;
    private final InputView inputView = new InputView();

    public BlackjackApplictaion(Trump trump) {
        this.trump = trump;
    }

    public void start() {
        NamesDto requestNames = inputView.requestPlayerName();
        List<Player> players = requestNames.names().stream()
                .map(inputView::requestBettingMoney)
                .map(playerDto -> Player.of(playerDto.name(), playerDto.bettingMoney()))
                .toList();

    }
}
