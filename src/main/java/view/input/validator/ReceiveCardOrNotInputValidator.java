package view.input.validator;

import static view.input.exception.InputExceptionMessage.NOT_Y_OR_N_INPUT;

import java.util.List;
import view.input.exception.InputException;

public class ReceiveCardOrNotInputValidator implements NotEmptyCheckable {
    private static final List<String> validInput = List.of("y", "n");

    public void validate(String userInput) {
        validateIsNotEmpty(userInput);
        validateIsYOrN(userInput);
    }

    private void validateIsYOrN(String userInput) {
        if (!validInput.contains(userInput)) {
            throw InputException.of(NOT_Y_OR_N_INPUT.getMessage());
        }
    }
}