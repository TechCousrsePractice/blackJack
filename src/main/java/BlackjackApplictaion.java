import domain.Option;
import domain.card.Trump;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import dto.NamesDto;
import dto.ResultDto;
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
        players.forEach(this::playPlayer);
        playDealer(dealer);
        displayResult(dealer, players);
        displayTotalProfit(getFightResults(players, dealer));
    }

    private List<ResultDto> getFightResults(List<Player> players, Dealer dealer) {
        return players.stream()
                .map(player -> getFightResult(dealer, player))
                .toList();
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

    private void playPlayer(Player player) {
        while (player.getScore() <= 21) {
            Option option = inputView.requestOption(player.getName());

            if (option == Option.NO) {
                outputView.displayPlayerCards(player);
                break;
            }

            addCardsByCount(player, 1);
            outputView.displayPlayerCards(player);
        }
    }

    private void playDealer(Dealer dealer) {
        if (dealer.canGetAdditionalCard()) {
            addCardsByCount(dealer, 1);
            outputView.displayDealerAdditionalMessage();
        }
    }

    private void displayResult(Dealer dealer, List<Player> players) {
        outputView.displayResults(dealer, players);
    }

    private ResultDto getFightResult(Dealer dealer, Player player) {
        if (dealer.isExplode()) {
            if (player.isExplode()) {
                return new ResultDto(player.getName(), 0);
            }
            return new ResultDto(player.getName(), player.getBettingMoney());
        }

        if (player.isExplode()) {
            return new ResultDto(player.getName(), -player.getBettingMoney());
        }

        if (player.getScore() > dealer.getScore()) {
            if (player.isBlackjack()) {
                return new ResultDto(player.getName(), player.getBettingMoney() * 1.5);
            }
            return new ResultDto(player.getName(), player.getBettingMoney());
        }

        if (player.getScore() < dealer.getScore()) {
            return new ResultDto(player.getName(), -player.getBettingMoney());
        }

        return new ResultDto(player.getName(), 0);
    }

    private void displayTotalProfit(List<ResultDto> fightResults) {
        outputView.displayTotalProfits(fightResults);
    }
}
