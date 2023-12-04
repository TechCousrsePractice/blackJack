package domain.util;

import java.util.Objects;

public class Validator {
    public static boolean isYOrN(String yOrN) {
        return Objects.equals(yOrN, "y");
    }
}
