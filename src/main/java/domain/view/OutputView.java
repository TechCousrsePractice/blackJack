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
}
