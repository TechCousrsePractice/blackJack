package controller;

import view.InputView;
import view.OutputView;

public class BlackJack {
    private final InputView inputView;
    private final OutputView outputView;
    private static BlackJack instance;

    private BlackJack(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public static BlackJack getInstance(InputView inputView, OutputView outputView) {
        if (instance == null) {
            instance = new BlackJack(inputView, outputView);
        }
        return instance;
    }

    public void run() {

    }
}
