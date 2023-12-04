package domain.view;

import camp.nextstep.edu.missionutils.Console;
import domain.user.Player;
import domain.util.Parser;
import domain.util.Validator;
import domain.view.constant.InputViewMessage;
import java.util.List;

public class InputView {
    public List<String> readPlayers() {
        InputViewMessage.ASK_MEMBER.printLine();
        return Parser.makeNames(Console.readLine());
    }

    public double readMoney(String name) {
        System.out.println();
        InputViewMessage.ASK_BETTING_MONEY.renderAndPrint(name);
        return Parser.parseMoney(Console.readLine());
    }

    public boolean reDrawCard(Player player) {
        System.out.println();
        InputViewMessage.ASK_RE_DRWAING.renderAndPrint(player.getName());
        return Validator.isYOrN(Parser.parseYAndN(Console.readLine()));
    }
}
