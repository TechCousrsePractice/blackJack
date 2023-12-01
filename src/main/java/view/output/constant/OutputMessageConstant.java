package view.output.constant;

public enum OutputMessageConstant {
    ;
    private final String message;

    OutputMessageConstant(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}