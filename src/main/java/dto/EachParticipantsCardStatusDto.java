package dto;

import java.util.List;

public record EachParticipantsCardStatusDto(String oneOfDealerCard, List<UserCardsDto> userCardsDto) {
    public static EachParticipantsCardStatusDto of(String oneOfDealerCard, List<UserCardsDto> userCardsDto) {
        return new EachParticipantsCardStatusDto(oneOfDealerCard, userCardsDto);
    }
}
