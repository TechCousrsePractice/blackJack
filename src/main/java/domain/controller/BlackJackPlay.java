package domain.controller;

import domain.user.Player;
import domain.user.Players;
import domain.view.InputView;
import domain.view.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BlackJackPlay {
    private final static int START_INDEX = 0;
    private final static InputView inputView = new InputView();
    private final static OutputView outputView = new OutputView();

    public void start() {
        Players players = readPlayersInfo();
    }

    private Players readPlayersInfo() {
        List<String> playerNames = inputView.readPlayers();
        List<Double> playerBettingMoney = playerNames.stream()
                .map(inputView::readMoney)
                .toList();

        return registerPlayers(playerNames, playerBettingMoney);
    }

    private Players registerPlayers(List<String> playerNames, List<Double> playerBettingMoney) {
        List<Player> players = new ArrayList<>();

        IntStream.range(START_INDEX, playerNames.size()).forEach(i ->
                players.add(new Player(playerNames.get(i), playerBettingMoney.get(i)))
        );

        return new Players(players);
    }
}
