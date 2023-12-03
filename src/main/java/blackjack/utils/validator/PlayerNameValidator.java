package blackjack.utils.validator;

import org.junit.platform.commons.util.StringUtils;

import java.util.Arrays;
import java.util.List;

import static blackjack.exception.ErrorMessage.INVALID_PLAYER_NAME;

public class PlayerNameValidator {
    public static List<String> safeSplit(String input, String delimiter) {
        validateEmpty(input);
        validateStartsOrEndsWithDelimiter(input, delimiter);
        return Arrays.stream(input.split(delimiter))
                .map(String::trim)
                .toList();
    }

    private static void validateEmpty(String input) {
        if (StringUtils.isBlank(input)) {
            throw new IllegalArgumentException(INVALID_PLAYER_NAME.getMessage());
        }
    }

    private static void validateStartsOrEndsWithDelimiter(String input, String delimiter) {
        if (input.startsWith(delimiter) || input.endsWith(delimiter)) {
            throw new IllegalArgumentException(INVALID_PLAYER_NAME.getMessage());
        }
    }
}
