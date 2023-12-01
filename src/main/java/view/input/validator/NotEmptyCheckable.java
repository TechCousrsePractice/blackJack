package view.input.validator;

import view.input.exception.InputException;
import view.input.exception.InputExceptionMessage;

public interface NotEmptyCheckable {
    default void validateIsNotEmpty(String userInput) {
        if (userInput.isBlank()) {
            throw InputException.of(InputExceptionMessage.IS_EMPTY.getMessage());
        }
    }
}