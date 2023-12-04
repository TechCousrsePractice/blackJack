package domain.view.constant;

public enum OutputViewMessage implements ViewMessage {
    CARD_DISTRIBUTION("딜러와 %s에게 2장을 나누었습니다."),
    DEALER_CARD("딜러: %s"),
    PLAYER_CARD("%s카드: %s"),
    DEALER_DRAW("딜러는 16이하라 한 장의 카드를 더 받았습니다."),
    PLAYER_CARD_RESULT("%s카드: %s - 결과: %d"),
    DEALER_CARD_RESULT("딜러 카드: %s - 결과: %d"),
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

    public void renderAndPrint(String s1, String s2, int d) {
        System.out.printf(message + System.lineSeparator(), s1, s2, d);
    }

    public void renderAndPrint(String s, int d) {
        System.out.printf(message + System.lineSeparator(), s, d);
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
