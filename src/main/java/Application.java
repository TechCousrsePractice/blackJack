import controller.BlackJack;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        BlackJack blackJack = BlackJack.getInstance(InputView.create(), OutputView.create());
        blackJack.run();
    }
}
