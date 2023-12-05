package blackjack.controller;

import blackjack.domain.participants.player.Player;
import blackjack.domain.participants.player.Players;
import blackjack.dto.PlayerNamesDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class MainController {
    private final InputView inputView;
    private final OutputView outputView;
    private GameController gameController;

    private MainController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public static MainController create() {
        return new MainController(InputView.getInstance(), OutputView.getInstance());
    }

    public void run() {
        Players players = createPlayers();
        gameController = GameController.of(inputView, outputView);
        gameController.play(players);
    }

    private Players createPlayers() {
        PlayerNamesDto playerNamesDto = createPlayerNames();
        List<Integer> bettingAmounts = createAllBettingAmounts(playerNamesDto);

        List<Player> players = IntStream.range(0, bettingAmounts.size())
                .mapToObj(i -> Player.from(playerNamesDto.names().get(i), bettingAmounts.get(i)))
                .toList();
        return Players.from(players);
    }

    private PlayerNamesDto createPlayerNames() {
        return readUserInput(() -> {
            List<String> playerNames = inputView.readPlayerNames();
            return new PlayerNamesDto(playerNames);
        });
    }

    private List<Integer> createAllBettingAmounts(PlayerNamesDto playerNamesDto) {
        return playerNamesDto.names().stream()
                .map(this::createBettingAmount)
                .toList();
    }

    private int createBettingAmount(String name) {
        return readUserInput(() -> inputView.readBettingAmount(name));
    }

    private <T> T readUserInput(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}
