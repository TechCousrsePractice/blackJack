package view.output;

import static view.output.constant.OutputSymbolConstant.NEW_LINE;

public class OutputView {

    private void print(String message) {
        System.out.print(message);
    }

    private void printLine() {
        print(NEW_LINE.getSymbol());
    }
}