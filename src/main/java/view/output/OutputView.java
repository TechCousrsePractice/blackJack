package view.output;

import static view.output.constant.OutputFormatConstant.INSERT_BETTING_MONEY_FORMAT;
import static view.output.constant.OutputMessageConstant.INSERT_PARTICIPANT_NAME;
import static view.output.constant.OutputSymbolConstant.NEW_LINE;

public class OutputView {

    public void askToInsertParticipantNames() {
        print(INSERT_PARTICIPANT_NAME.getMessage());
        printLine();
    }

    public void askToInsertBettingMoney(String userName) {
        print(String.format(INSERT_BETTING_MONEY_FORMAT.getFormat(), userName));
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
}