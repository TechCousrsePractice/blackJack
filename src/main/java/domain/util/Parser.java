package domain.util;

import domain.view.constant.ErrorMessage;
import java.util.Arrays;
import java.util.List;
import java.util.regex.PatternSyntaxException;

public class Parser {
    private static final String DELIMITER = ",";
    private static final String BLANK = " ";
    private static final String EMPTY = "";

    public static List<String> makeNames(String names) {
        try {
            return parseNames(removeBlank(names).split(DELIMITER));
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException();
        }
    }

    private static List<String> parseNames(String[] names) {
        return Arrays.stream(names)
                .toList();
    }

    public static double parseMoney(String money) {
        try {
            return Double.parseDouble(removeBlank(money));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.NOT_A_NUMBER.getErrorMessage());
        }
    }

    public static String removeBlank(String s) {
        return s.trim()
                .replaceAll(BLANK, EMPTY);
    }
}
