package blackjack.utils.validator;

import static blackjack.exception.ErrorMessage.NOT_NUMERIC_INPUT;
import static blackjack.exception.ErrorMessage.NOT_POSITIVE_INPUT;

public class BettingAmountValidator {
    public static int safeParseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_NUMERIC_INPUT.getMessage());
        }
    }

    public static void validatePositive(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException(NOT_POSITIVE_INPUT.getMessage());
        }
    }
}
