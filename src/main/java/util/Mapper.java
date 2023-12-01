package util;

import dto.PlayersDto;
import java.util.List;
import java.util.stream.Stream;

public class Mapper {
    private static final String NAME_DELEMITER = ",";

    public static PlayersDto toPlayerDto(String request) {
        List<String> requestNames = Stream.of(request.split(NAME_DELEMITER))
                .map(String::trim)
                .toList();

        validatePlayerNameRequest(requestNames);

        return new PlayersDto(requestNames);
    }

    private static void validatePlayerNameRequest(List<String> requestNames) {
        requestNames.forEach(Mapper::validateEmpty);
        validateUnique(requestNames);
    }

    private static void validateEmpty(String request) {
        if (request.isBlank()) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateUnique(List<String> requestNames) {
        if (isNotUnique(requestNames)) {
            throw new IllegalArgumentException();
        }
    }

    private static boolean isNotUnique(List<String> requestNames) {
        return requestNames.stream()
                .distinct()
                .count() != requestNames.size();
    }
}
