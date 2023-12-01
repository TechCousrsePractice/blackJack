package controller;

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
        participantNames.forEach(
                System.out::println
        );
    }

    private List<String> getParticipantsNames() {
        return readUserInput(() -> {
            outputView.askToInsertParticipantNames();
            return inputView.getParticipantNames();
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