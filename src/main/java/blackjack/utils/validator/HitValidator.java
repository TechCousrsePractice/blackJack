package blackjack.utils.validator;

import static blackjack.exception.ErrorMessage.INVALID_HIT_INPUT;

public class HitValidator {
    public static boolean safeParse(String input) {
        if (input.equals("y")) {
            return true;
        }
        if (input.equals("n")) {
            return false;
        }
        throw new IllegalArgumentException(INVALID_HIT_INPUT.getMessage());
    }
}
