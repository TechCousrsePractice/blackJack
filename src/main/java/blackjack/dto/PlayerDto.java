package blackjack.dto;

public record PlayerDto(String name, CardsDto cardsDto, int totalScore) {
}
