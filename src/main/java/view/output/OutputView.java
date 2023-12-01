package view.output;

import static view.output.constant.OutputFormatConstant.ASK_TO_RECEIVE_ONE_CARD;
import static view.output.constant.OutputFormatConstant.DEALER_CARD_INIT_STATUS_SHOW_FORMAT;
import static view.output.constant.OutputFormatConstant.INSERT_BETTING_MONEY_FORMAT;
import static view.output.constant.OutputFormatConstant.NOTIFY_ALL_CARDS_ARE_DISTRIBUTED_FORMAT;
import static view.output.constant.OutputFormatConstant.PLAYER_CARD_AFTER_STATUS_SHOW_FORMAT;
import static view.output.constant.OutputFormatConstant.PLAYER_CARD_INIT_STATUS_SHOW_FORMAT;
import static view.output.constant.OutputMessageConstant.DEALER_GOT_ONE_CARD;
import static view.output.constant.OutputMessageConstant.INSERT_PARTICIPANT_NAME;
import static view.output.constant.OutputNumberConstant.PLAYER_FIRST_CARD_INDEX;
import static view.output.constant.OutputNumberConstant.PLAYER_SECOND_CARD_INDEX;
import static view.output.constant.OutputSymbolConstant.NEW_LINE;

import dto.EachParticipantsCardStatusDto;
import dto.UserCardsDto;
import java.util.List;

public class OutputView {

    public void askToInsertParticipantNames() {
        print(INSERT_PARTICIPANT_NAME.getMessage());
        printLine();
    }

    public void askToInsertBettingMoney(String userName) {
        print(String.format(INSERT_BETTING_MONEY_FORMAT.getFormat(), userName));
        printLine();
    }

    public void notifyALlParticipantsGetCards(List<String> participantNames) {
        printLine();
        String notifyFormat = String.format(NOTIFY_ALL_CARDS_ARE_DISTRIBUTED_FORMAT.getFormat(),
                generateDynamicFormatString(participantNames.size()));
        print(String.format(notifyFormat, participantNames.toArray()));
        printLine();
    }

    public void showEachParticipantsCardStatus(EachParticipantsCardStatusDto eachParticipantsCardStatusDto) {
        print(String.format(DEALER_CARD_INIT_STATUS_SHOW_FORMAT.getFormat(),
                eachParticipantsCardStatusDto.oneOfDealerCard()));
        printLine();
        eachParticipantsCardStatusDto.userCardsDto().forEach(
                userCardsDto -> {
                    print(String.format(PLAYER_CARD_INIT_STATUS_SHOW_FORMAT.getFormat(), userCardsDto.userName(),
                            userCardsDto.cardNames().get(PLAYER_FIRST_CARD_INDEX.getNumber()),
                            userCardsDto.cardNames().get(PLAYER_SECOND_CARD_INDEX.getNumber())));
                    printLine();
                }
        );
    }

    public void showParticipantCardStatus(UserCardsDto userCardsDto) {
        print(String.format(PLAYER_CARD_AFTER_STATUS_SHOW_FORMAT.getFormat(), userCardsDto.userName()));
        print(String.format(generateDynamicFormatString(userCardsDto.getCardSize()),
                userCardsDto.cardNames().toArray()));
        printLine();
    }

    public void askPlayerToReceiveCard(String playerName) {
        printLine();
        print(String.format(ASK_TO_RECEIVE_ONE_CARD.getFormat(), playerName));
        printLine();
    }

    public void showDealerGotOneCard() {
        printLine();
        print(DEALER_GOT_ONE_CARD.getMessage());
        printLine();
    }

    public void printError(String message) {
        print(message);
        printLine();
    }

    private void print(String message) {
        System.out.print(message);
    }

    private void printLine() {
        print(NEW_LINE.getSymbol());
    }

    private String generateDynamicFormatString(int numberOfPlaceholders) {
        StringBuilder formatBuilder = new StringBuilder();
        for (int i = 0; i < numberOfPlaceholders; i++) {
            formatBuilder.append("%s");
            if (i < numberOfPlaceholders - 1) {
                formatBuilder.append(", ");
            }
        }
        return formatBuilder.toString();
    }

}