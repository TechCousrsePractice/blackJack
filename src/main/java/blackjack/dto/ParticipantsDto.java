package blackjack.dto;

import java.util.List;

public record ParticipantsDto(DealerDto dealerDto, List<PlayerDto> playerDtos) {
}
