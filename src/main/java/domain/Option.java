package domain;

import java.util.Arrays;
import java.util.Optional;

public enum Option {
    YES("y"),
    NO("n");

    private final String option;

    Option(String option) {
        this.option = option;
    }

    public static Optional<Option> findOption(String request) {
        return Arrays.stream(Option.values())
                .filter(value -> request.equals(value.option))
                .findFirst();
    }
}
