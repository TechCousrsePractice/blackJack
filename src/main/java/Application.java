import camp.nextstep.edu.missionutils.Console;
import controller.BlackjackController;
import view.input.InputView;
import view.output.OutputView;

public class Application {
    public static void main(String[] args) {
        BlackjackController racingCarController = new BlackjackController(new InputView(), new OutputView());
        racingCarController.execute();
        Console.close();
    }
}