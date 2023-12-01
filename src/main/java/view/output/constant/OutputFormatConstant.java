package view.output.constant;

public enum OutputFormatConstant {
    INSERT_BETTING_MONEY_FORMAT("%s의 배팅 금액은?"),
    NOTIFY_ALL_CARDS_ARE_DISTRIBUTED_FORMAT("딜러와 %s에게 2장을 나누었습니다."),
    DEALER_CARD_INIT_STATUS_SHOW_FORMAT("딜러: %s"),
    PLAYER_CARD_INIT_STATUS_SHOW_FORMAT("%s카드: %s, %s"),
    ASK_TO_RECEIVE_ONE_CARD("%s는 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");

    private final String format;

    OutputFormatConstant(final String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}