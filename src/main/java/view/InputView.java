package view;

import static camp.nextstep.edu.missionutils.Console.readLine;
import static view.constants.InputConstants.ADD_CARD;
import static view.constants.InputConstants.BETTING_AMOUNT_INIT;
import static view.constants.InputConstants.GAME_INIT;

import java.util.List;
import view.validate.InputValidate;

public class InputView {
    private InputValidate inputValidate;

    private InputView() {
        this.inputValidate = InputValidate.create();
    }
    public static InputView create() {
        return new InputView();
    }

    public List<String> enterGamer() {
        System.out.println(GAME_INIT);
        return inputValidate.IsNames(readLine());
    }

    public int enterBettingAmount(String name) {
        System.out.printf(BETTING_AMOUNT_INIT, name);
        return inputValidate.isAmount(readLine());
    }

    public String enterAddCard(String name) {
        System.out.printf(ADD_CARD, name);
        return inputValidate.isYesOrNo(readLine());
    }
}
