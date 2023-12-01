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
        requestNames.forEach(Mapper::validateBlank);
        validateEmpty(requestNames);
        validateUnique(requestNames);
    }

    private static void validateEmpty(List<String> requestNames) {
        if (requestNames.size() == 0) {
            throw new IllegalArgumentException("빈 입력");
        }
    }

    private static void validateBlank(String request) {
        if (request.isBlank()) {
            throw new IllegalArgumentException("빈 입력");
        }
    }

    private static void validateUnique(List<String> requestNames) {
        if (isNotUnique(requestNames)) {
            throw new IllegalArgumentException("이름 중복");
        }
    }

    private static boolean isNotUnique(List<String> requestNames) {
        return requestNames.stream()
                .distinct()
                .count() != requestNames.size();
    }
}
