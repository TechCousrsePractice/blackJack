package view.output;

import static view.output.constant.OutputFormatConstant.INSERT_BETTING_MONEY_FORMAT;
import static view.output.constant.OutputFormatConstant.NOTIFY_ALL_CARDS_ARE_DISTRIBUTED_FORMAT;
import static view.output.constant.OutputMessageConstant.INSERT_PARTICIPANT_NAME;
import static view.output.constant.OutputSymbolConstant.NEW_LINE;

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
        String notifyFormat = String.format(NOTIFY_ALL_CARDS_ARE_DISTRIBUTED_FORMAT.getFormat(),
                generateDynamicFormatString(participantNames.size()));
        print(String.format(notifyFormat, participantNames.toArray()));
        printLine();
    }

    public void showEachParticipantsCardStatus() {
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