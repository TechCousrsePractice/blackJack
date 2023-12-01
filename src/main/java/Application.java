import domain.card.Card;
import domain.card.CardFactory;
import domain.card.Trump;
import java.util.Map;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        Map<Card, Boolean> trump = CardFactory.create().stream()
                .collect(Collectors.toMap(
                        card -> card,
                        usage -> false
                ));
        BlackjackApplictaion blackjackApplictaion = new BlackjackApplictaion(new Trump(trump));
        blackjackApplictaion.start();
    }
}
