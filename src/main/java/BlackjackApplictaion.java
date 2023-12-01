import domain.card.Trump;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import dto.NamesDto;
import java.util.List;
import java.util.stream.IntStream;
import view.InputView;
import view.OutputView;

public class BlackjackApplictaion {
    private final Trump trump;
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public BlackjackApplictaion(Trump trump) {
        this.trump = trump;
    }

    public void start() {
        NamesDto requestNames = inputView.requestPlayerName();
        List<Player> players = generatePlayer(requestNames);
        Dealer dealer = new Dealer();
        playIntial(dealer, players);
    }

    private List<Player> generatePlayer(NamesDto requestNames) {
        return requestNames.names().stream()
                .map(inputView::requestBettingMoney)
                .map(playerDto -> Player.of(playerDto.name(), playerDto.bettingMoney()))
                .toList();
    }

    private void playIntial(Dealer dealer, List<Player> players) {
        addCardsByCount(dealer, 2);
        players.forEach(player -> addCardsByCount(player, 2));
        outputView.displayInitialCards(dealer, players);
    }

    private void addCardsByCount(User user, int count) {
        IntStream.range(0, count)
                .forEach(attempt -> user.addCard(trump.getRandomCard()));
    }

}
