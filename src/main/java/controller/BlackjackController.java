package controller;

import java.util.ArrayList;
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
        List<String> participantNames = getParticipantsNames();
        List<Double> eachParticipantBettingMoney = new ArrayList<>();
        for (String participantName : participantNames) {
            eachParticipantBettingMoney.add(getParticipantsBettingMoney(participantName));
        }
        participantNames.forEach(pN -> System.out.print(pN + "\t"));
        System.out.println();
        eachParticipantBettingMoney.forEach(ePBM -> System.out.print(ePBM + "\t"));
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