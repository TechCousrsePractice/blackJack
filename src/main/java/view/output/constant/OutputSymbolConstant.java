package view.output.constant;

public enum OutputSymbolConstant {
    NEW_LINE(System.lineSeparator());

    private final String symbol;

    OutputSymbolConstant(final String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}