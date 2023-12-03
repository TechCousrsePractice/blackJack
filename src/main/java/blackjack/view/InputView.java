package blackjack.view;

import blackjack.dto.PlayerDto;
import blackjack.utils.validator.BettingAmountValidator;
import blackjack.utils.validator.HitValidator;
import blackjack.utils.validator.PlayerNameValidator;
import camp.nextstep.edu.missionutils.Console;

import java.util.List;

public class InputView {
    private static final InputView instance = new InputView();
    private static final String ASK_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String PLAYER_NAME_DELIMITER = ",";
    private static final String ASK_BETTING_AMOUNT_FORMAT = "%s의 배팅 금액은?";
    private static final String ASK_HIT_FORMAT = "%s는 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";


    private InputView() {
    }

    public static InputView getInstance() {
        return instance;
    }

    public List<String> readPlayerNames() {
        System.out.println(ASK_PLAYER_NAMES);
        String input = Console.readLine();
        return PlayerNameValidator.safeSplit(input, PLAYER_NAME_DELIMITER);
    }

    public int readBettingAmount(String name) {
        printLine();
        System.out.printf((ASK_BETTING_AMOUNT_FORMAT) + "%n", name);
        String input = Console.readLine();
        int bettingAmount = BettingAmountValidator.safeParseInt(input);
        BettingAmountValidator.validatePositive(bettingAmount);
        return bettingAmount;
    }

    private void printLine() {
        System.out.println();
    }

    public boolean readHit(PlayerDto playerDto) {
        printLine();
        System.out.printf((ASK_HIT_FORMAT) + "%n", playerDto.name());
        String input = Console.readLine();
        return HitValidator.safeParse(input);
    }
}
