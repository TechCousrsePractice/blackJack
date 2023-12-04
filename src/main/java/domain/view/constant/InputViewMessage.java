package domain.view.constant;

public enum InputViewMessage implements ViewMessage {
    ASK_MEMBER("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"),
    ASK_BETTING_MONEY("%s의 배팅 금액은?"),
    ;

    private final String message;

    InputViewMessage(String message) {
        this.message = message;
    }

    public void renderAndPrint(String name) {
        System.out.printf(message + System.lineSeparator(), name);
    }

    @Override
    public void print() {
        System.out.print(message);
    }

    @Override
    public void printLine() {
        System.out.println(message);
    }
}
