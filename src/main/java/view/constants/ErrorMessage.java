package view.constants;

public enum ErrorMessage {
    PREFIX("ERROR : "),
    IS_INTEGER("숫자만 입력 가능합니다."),
    IS_YES_OR_NO("y, n만 입력 가능합니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX.message + message;
    }
}
