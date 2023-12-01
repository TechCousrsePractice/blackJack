package view;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;
import java.util.List;

public class OutputView {
    private static final String DELIMITER = ", ";
    private static final String INITIAL_CARD_MESSAGE = "딜러와 %s에게 2장을 나누었습니다.";
    private static final String CARDS_DISPLAY_FORMAT = "%s: %s";
    private static final String DEALER_ADDITIONAL_MESSAGE="딜러는 16이하라 한 장의 카드를 더 받았습니다."

    public void displayInitialCards(Dealer dealer, List<Player> players) {
        System.out.println();
        System.out.println(String.format(INITIAL_CARD_MESSAGE, joiner(getNames(players))));
        displayDealerCards(dealer.getCard());
        players.forEach(this::displayPlayerCards);
    }

    private void displayDealerCards(Card card) {
        displayCards("딜러", card.toString());
    }

    public void displayPlayerCards(Player player) {
        List<String> cards = player.getCurrentCards().stream()
                .map(Card::toString)
                .toList();

        displayCards(player.getName() + "카드", joiner(cards));
    }

    private void displayCards(String name, String cards) {
        System.out.println(String.format(CARDS_DISPLAY_FORMAT, name, cards));
    }

    private List<String> getNames(List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    private String joiner(List<String> targets) {
        return String.join(DELIMITER, targets);
    }

    public void displayDealerAdditionalMessage() {
        System.out.println(DEALER_ADDITIONAL_MESSAGE);
    }
}
