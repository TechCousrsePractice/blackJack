package view.output.constant;

public enum OutputFormatConstant {
    INSERT_BETTING_MONEY_FORMAT("%s의 배팅 금액은?");

    private final String format;

    OutputFormatConstant(final String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}