package util;

import dto.NamesDto;
import dto.PlayerDto;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Mapper {
    private static final String NAME_DELEMITER = ",";

    private static final Pattern NUMERIC = Pattern.compile("[0-9]+");

    public static NamesDto toNamesDto(String request) {
        List<String> requestNames = Stream.of(request.split(NAME_DELEMITER))
                .map(String::trim)
                .toList();

        validatePlayerNameRequest(requestNames);

        return new NamesDto(requestNames);
    }

    private static void validatePlayerNameRequest(List<String> requestNames) {
        requestNames.forEach(Mapper::validateBlank);
        validateEmpty(requestNames);
        validateUnique(requestNames);
    }

    private static void validateEmpty(List<String> requestNames) {
        if (requestNames.isEmpty()) {
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

    public static PlayerDto toPlayerDto(String name, String requestMoney) {
        validateRequestMoney(requestMoney);
        return new PlayerDto(name, toInt(requestMoney));
    }

    private static void validateRequestMoney(String requestMoney) {
        validateBlank(requestMoney);
        validateNumeric(requestMoney);
    }

    private static void validateNumeric(String request) {
        if (!NUMERIC.matcher(request).matches()) {
            throw new IllegalArgumentException("숫자가 아님");
        }
    }

    private static int toInt(String request) {
        return Integer.parseInt(request);
    }
}
