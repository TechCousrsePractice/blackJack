package blackjack.view;

import blackjack.dto.*;

import java.util.List;

public class OutputView {
    private static final OutputView instance = new OutputView();
    private static final String CARD_DISTRIBUTION_TITLE_FORMAT = "딜러와 %s에게 2장을 나누었습니다.";
    private static final String CARD_DISTRIBUTION_NAME_DELIMITER = ", ";
    private static final String DEALER_CARD_FORMAT = "딜러: %s";
    private static final String PLAYER_CARD_FORMAT = "%s카드: %s";
    private static final String CARDS_DELIMITER = ", ";
    private static final String DEALER_DRAW_MESSAGE = "딜러는 16이하라 한 장의 카드를 더 받았습니다.";
    private static final String DEALER_CARD_RESULT_FORMAT = "딜러 카드: %s - 결과: %d";
    private static final String PLAYER_CARD_RESULT_FORMAT = " - 결과: %d";
    private static final String PROFIT_TITLE = "## 최종 수익";
    private static final String DEALER_PROFIT_FORMAT = "딜러: %.2f";
    private static final String PLAYER_PROFIT_FORMAT = "%s: %.2f";


    private OutputView() {
    }

    public static OutputView getInstance() {
        return instance;
    }

    public void printError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void printCardDistribution(ParticipantsDto participantsDto) {
        printLine();
        printDistributionTitle(participantsDto.playerDtos());
        CardDto dealerCard = participantsDto.dealerDto().cardsDto().cardDtos().get(0);
        printDealerCard(dealerCard);
        printPlayersCards(participantsDto);
    }

    private void printPlayersCards(ParticipantsDto participantsDto) {
        participantsDto.playerDtos().forEach(this::printCardStatus);
    }

    private void printCardStatus(PlayerDto playerDto) {
        System.out.println(makeCardStatusMessage(playerDto));
    }

    private String makeCardStatusMessage(PlayerDto playerDto) {
        return String.format((PLAYER_CARD_FORMAT), playerDto.name(), makeCardsFormat(playerDto.cardsDto()));
    }

    private void printLine() {
        System.out.println();
    }

    private void printDistributionTitle(List<PlayerDto> playerDtos) {
        List<String> names = playerDtos.stream()
                .map(PlayerDto::name)
                .toList();
        System.out.printf((CARD_DISTRIBUTION_TITLE_FORMAT) + "%n",
                String.join(CARD_DISTRIBUTION_NAME_DELIMITER, names));
    }

    private void printDealerCard(CardDto dealerCard) {
        System.out.printf((DEALER_CARD_FORMAT) + "%n", makeCardFormat(dealerCard));
    }

    private String makeCardsFormat(CardsDto cardsDto) {
        List<String> cards = cardsDto.cardDtos().stream()
                .map(this::makeCardFormat)
                .toList();
        return String.join(CARDS_DELIMITER, cards);
    }

    private String makeCardFormat(CardDto cardDto) {
        return cardDto.value() + cardDto.shape();
    }

    public void printUpdatedCardStatus(PlayerDto playerDto) {
        printLine();
        printCardStatus(playerDto);
    }

    public void printDealerDraw() {
        printLine();
        System.out.println(DEALER_DRAW_MESSAGE);
    }
    
    public void printResult(ParticipantsDto participantsDto, ProfitDto profitDto) {
        printResultCardStatus(participantsDto);
        printProfit(profitDto);
    }

    private void printResultCardStatus(ParticipantsDto participantsDto) {
        printLine();
        DealerDto dealerDto = participantsDto.dealerDto();
        printDealerResultCardStatus(dealerDto);
        printAllPlayersResultCardStatus(participantsDto);
    }

    private void printDealerResultCardStatus(DealerDto dealerDto) {
        System.out.printf((DEALER_CARD_RESULT_FORMAT) + "%n", makeCardsFormat(dealerDto.cardsDto()), dealerDto.totalScore());
    }

    private void printAllPlayersResultCardStatus(ParticipantsDto participantsDto) {
        participantsDto.playerDtos().forEach(this::printPlayerResultCardStatus);
    }

    private void printPlayerResultCardStatus(PlayerDto playerDto) {
        String message = makeCardStatusMessage(playerDto) + String.format(PLAYER_CARD_RESULT_FORMAT, playerDto.totalScore());
        System.out.println(message);
    }

    private void printProfit(ProfitDto profitDto) {
        printLine();
        System.out.println(PROFIT_TITLE);
        printDealerProfit(profitDto.dealerProfit());
        printPlayersProfit(profitDto.playerProfitDtos());
    }

    private void printDealerProfit(double dealerProfit) {
        System.out.printf((DEALER_PROFIT_FORMAT) + "%n", dealerProfit);
    }

    private void printPlayersProfit(List<PlayerProfitDto> playerProfitDtos) {
        playerProfitDtos.forEach(playerProfitDto ->
                        System.out.printf((PLAYER_PROFIT_FORMAT) + "%n", playerProfitDto.name(), playerProfitDto.profit()));
    }
}
