package view.output.constant;

public enum OutputMessageConstant {
    INSERT_PARTICIPANT_NAME("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"),
    DEALER_GOT_ONE_CARD("딜러는 16이하라 한 장의 카드를 더 받았습니다.");
    private final String message;

    OutputMessageConstant(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}