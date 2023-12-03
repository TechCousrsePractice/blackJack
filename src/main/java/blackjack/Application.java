package blackjack;

import blackjack.controller.MainController;
import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        MainController mainController = MainController.create();
        mainController.run();
        Console.close();
    }
}
