package view.input.exception;

import static global.Constants.ERROR_PREFIX;

public enum InputExceptionMessage {
    IS_EMPTY("비어있는 입력입니다."),
    NOT_NUMERIC_TYPE("숫자 형식의 입력이 아닙니다."),
    NUMERIC_INPUT_UNDER_LOWER_LIMIT("양수 형식의 입력이 아닙니다."),
    INPUT_DOES_NOT_CONTAIN_DELIMITER("구분자(,)를 포함하지 않은 입력입니다."),
    NOT_Y_OR_N_INPUT("y 또는 n이 아닌 입력값입니다.");

    private final String message;

    InputExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX + message;
    }
}