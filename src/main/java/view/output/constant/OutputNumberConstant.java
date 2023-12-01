package view.output.constant;

public enum OutputNumberConstant {
    PLAYER_FIRST_CARD_INDEX(0),
    PLAYER_SECOND_CARD_INDEX(1);
    private final int number;

    OutputNumberConstant(final int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}