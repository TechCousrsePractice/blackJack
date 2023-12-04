package domain.view.constant;

public enum OutputViewMessage implements ViewMessage {
    CARD_DISTRIBUTION("딜러와 %s에게 2장을 나누었습니다."),
    DEALER_CARD("딜러: %s"),
    PLAYER_CARD("%s카드: %s"),
    ;

    private final String message;

    OutputViewMessage(String message) {
        this.message = message;
    }

    @Override
    public void renderAndPrint(String s) {
        System.out.printf(message + System.lineSeparator(), s);
    }
    
    public void renderAndPrint(String s1, String s2) {
        System.out.printf(message + System.lineSeparator(), s1, s2);
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
