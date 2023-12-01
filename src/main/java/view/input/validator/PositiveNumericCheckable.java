package view.input.validator;

import static view.input.constant.InputNumberConstant.NUMERIC_INPUT_UNDER_LIMIT_VALUE;

import view.input.exception.InputException;
import view.input.exception.InputExceptionMessage;

public interface PositiveNumericCheckable {

    default void validateIsNumeric(String userInput) {
        try {
            parseToInt(userInput);
        } catch (NumberFormatException e) {
            throw InputException.of(InputExceptionMessage.NOT_NUMERIC_TYPE.getMessage());
        }
    }

    default void validateIsPositive(String userInput) {
        if (parseToInt(userInput) <= NUMERIC_INPUT_UNDER_LIMIT_VALUE.getNumber()) {
            throw InputException.of(InputExceptionMessage.NUMERIC_INPUT_UNDER_LOWER_LIMIT.getMessage());
        }
    }

    private int parseToInt(String userInput) {
        return Integer.parseInt(userInput);
    }
}