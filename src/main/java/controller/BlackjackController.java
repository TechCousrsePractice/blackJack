package controller;

import domain.user.Player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import view.input.InputView;
import view.input.exception.InputException;
import view.output.OutputView;

public class BlackjackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void execute() {
        List<Player> players = generatePlayers();
        players.forEach(
                player -> {
                    System.out.println(player.toString());
                }
        );
    }

    private List<Player> generatePlayers() {
        List<Player> players = new ArrayList<>();

        List<String> participantNames = getParticipantsNames();
        participantNames.forEach(
                participantName -> {
                    players.add(Player.of(participantName, getParticipantsBettingMoney(participantName)));
                }
        );

        return Collections.unmodifiableList(players);
    }

    private List<String> getParticipantsNames() {
        return readUserInput(() -> {
            outputView.askToInsertParticipantNames();
            return inputView.getParticipantNames();
        });
    }

    private Double getParticipantsBettingMoney(String participantName) {
        return readUserInput(() -> {
            outputView.askToInsertBettingMoney(participantName);
            return inputView.getParticipantBettingMoney();
        });
    }

    private <T> T readUserInput(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (InputException e) {
                outputView.printError(e.getMessage());
            }
        }
    }


}