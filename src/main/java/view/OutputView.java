package view;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import dto.ResultDto;
import java.util.List;

public class OutputView {
    private static final String DELIMITER = ", ";
    private static final String INITIAL_CARD_MESSAGE = "딜러와 %s에게 2장을 나누었습니다.";
    private static final String CARDS_DISPLAY_FORMAT = "%s: %s";
    private static final String DEALER_ADDITIONAL_MESSAGE = "딜러는 16이하라 한 장의 카드를 더 받았습니다.";
    private static final String RESULT_DISPLAY_FORMAT = "%s - 결과 : %d";
    private static final String PROFIT_FORMAT = "%s: %d";

    public void displayInitialCards(Dealer dealer, List<Player> players) {
        System.out.println();
        System.out.println(String.format(INITIAL_CARD_MESSAGE, joiner(getNames(players))));
        System.out.println(displayDealerCards(dealer.getCard(), "딜러"));
        players.forEach(this::displayPlayerCards);
    }

    public void displayPlayerCards(Player player) {
        System.out.println(getPlayerCardsMessage(player.getCurrentCards(), player.getName() + "카드"));
    }

    private String displayDealerCards(Card card, String name) {
        return getCardsDisplayFormat(name, card.toString());
    }

    public String getPlayerCardsMessage(List<Card> currentCards, String name) {
        List<String> cards = currentCards.stream()
                .map(Card::toString)
                .toList();

        return getCardsDisplayFormat(name, joiner(cards));
    }

    private String getCardsDisplayFormat(String name, String cards) {
        return String.format(CARDS_DISPLAY_FORMAT, name, cards);
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

    public void displayResults(Dealer dealer, List<Player> players) {
        System.out.println(getResultMessage("딜러 카드", dealer));
        players.forEach(
                player -> System.out.println(getResultMessage(player.getName() + "카드", player)));

    }

    private String getResultMessage(String name, User user) {
        String message = getPlayerCardsMessage(user.getCurrentCards(), name);
        return String.format(RESULT_DISPLAY_FORMAT, message, user.getScore());
    }

    public void displayTotalProfits(List<ResultDto> results) {
        System.out.println("## 최종 수익");
        double dealerProfit = -results.stream()
                .map(ResultDto::profit)
                .reduce((double) 0, Double::sum);
        displayProfit("딜러", (int) dealerProfit);
        results.forEach(result -> displayProfit(result.name(), (int) result.profit()));
    }

    private void displayProfit(String name, int profit) {
        System.out.println(String.format(PROFIT_FORMAT, name, profit));
    }
}
