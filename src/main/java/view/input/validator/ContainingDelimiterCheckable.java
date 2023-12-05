package view.input.validator;


import static view.input.constant.InputSymbolConstant.PARTICIPANT_NAME_DELIMITER;
import static view.input.exception.InputExceptionMessage.INPUT_DOES_NOT_CONTAIN_DELIMITER;
import static view.input.exception.InputExceptionMessage.IS_EMPTY;

import view.input.exception.InputException;

public interface ContainingDelimiterCheckable {
    default void validateContainsDelimiter(String userInput) {
        if (!userInput.contains(PARTICIPANT_NAME_DELIMITER.getSymbol())) {
            throw InputException.of(INPUT_DOES_NOT_CONTAIN_DELIMITER.getMessage());
        }
    }

    default void validateIsNotEndWithDelimiter(String userInput) {
        if (userInput.endsWith(PARTICIPANT_NAME_DELIMITER.getSymbol())) {
            throw InputException.of(IS_EMPTY.getMessage());
        }
    }
}