package view.input.constant;

public enum InputNumberConstant {
    NUMERIC_INPUT_UNDER_LIMIT_VALUE(0);
    private final int number;

    InputNumberConstant(final int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}