package blackjack.dto;

import java.util.List;

public record ProfitDto(double dealerProfit, List<PlayerProfitDto> playerProfitDtos) {
}
