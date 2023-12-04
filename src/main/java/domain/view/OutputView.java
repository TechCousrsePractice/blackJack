package domain.view;

import domain.user.Dealer;
import domain.user.Player;
import domain.view.constant.OutputViewMessage;

public class OutputView {
    public void printSetTable(String playerNames) {
        System.out.println();
        OutputViewMessage.CARD_DISTRIBUTION.renderAndPrint(playerNames);
    }

    public void printDealerCard(Dealer dealer) {
        OutputViewMessage.DEALER_CARD.renderAndPrint(dealer.getCurrentlyCards());
    }

    public void printPlayerCard(Player player) {
        OutputViewMessage.PLAYER_CARD.renderAndPrint(player.getName(), player.getCurrentlyCard());
    }

    public void printDealerDraw() {
        System.out.println();
        OutputViewMessage.DEALER_DRAW.print();
    }

    public void printPlayerCardResult(Player player) {
        OutputViewMessage.PLAYER_CARD_RESULT.renderAndPrint(player.getName(), player.getCurrentlyCard(),
                player.getCardSum());
    }

    public void printDealerCardResult(Dealer dealer) {
        OutputViewMessage.DEALER_CARD_RESULT.renderAndPrint(dealer.getCurrentlyCards(), dealer.getCardSum());
    }

    public void printProfit() {
        OutputViewMessage.PROFIT.printLine();
    }

    public void printProfitResult(String name, int money) {
        OutputViewMessage.PROFIT_RESULT.renderAndPrint(name, money);
    }
}
