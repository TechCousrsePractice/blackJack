import camp.nextstep.edu.missionutils.Console;
import domain.controller.BlackJackPlay;

public class Application {
    public static void main(String[] args) {
        BlackJackPlay blackJackPlay = new BlackJackPlay();
        blackJackPlay.start();
        Console.close();
    }
}
