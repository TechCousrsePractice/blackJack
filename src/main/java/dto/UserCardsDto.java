package dto;

import java.util.List;

public record UserCardsDto(String userName, List<String> cardNames) {
    public static UserCardsDto of(String userName, List<String> cardNames) {
        return new UserCardsDto(userName, cardNames);
    }

    public int getCardSize() {
        return cardNames.size();
    }
}