package blackjack.domain.card;

import java.util.Arrays;

import static blackjack.exception.ErrorMessage.INVALID_CARD_SHAPE;
import static blackjack.exception.ErrorMessage.INVALID_CARD_VALUE;

public enum Value {
    ACE("A", 1, 11),
    TWO("2", 2, 0),
    THREE("3", 3, 0),
    FOUR("4", 4, 0),
    FIVE("5", 5, 0),
    SIX("6", 6, 0),
    SEVEN("7", 7, 0),
    EIGHT("8", 8, 0),
    NINE("9", 9, 0),
    TEN("10", 10, 0),
    JACK("J", 10, 0),
    QUEEN("Q", 10, 0),
    KING("K", 10, 0);

    private final String valueName;
    private final int number;
    private final int optionalNumber;

    Value(String valueName, int number, int optionalNumber) {
        this.valueName = valueName;
        this.number = number;
        this.optionalNumber = optionalNumber;
    }

    public static Value getValue(String name) {
        return Arrays.stream(Value.values())
                .filter(value -> value.getValueName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_CARD_SHAPE.getMessage()));
    }

    public String getValueName() {
        return valueName;
    }

    public int getNumber() {
        return number;
    }

    public int getOptionalNumber() {
        if (this.optionalNumber != 0) {
            return optionalNumber;
        }
        throw new IllegalStateException(INVALID_CARD_VALUE.getMessage());
    }
}
