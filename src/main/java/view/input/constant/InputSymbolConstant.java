package view.input.constant;

public enum InputSymbolConstant {
    PARTICIPANT_NAME_DELIMITER(","), // 미션에 맞는 delimiter 로 바꿔주면 됨.
    BLANK(" "),
    VOID("");

    private final String symbol;

    InputSymbolConstant(final String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}