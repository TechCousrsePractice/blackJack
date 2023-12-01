package view;

import static camp.nextstep.edu.missionutils.Console.readLine;

import domain.Option;
import dto.NamesDto;
import dto.PlayerDto;
import java.util.Optional;
import util.Mapper;

public class InputView {
    private static final String REQUEST_PLAYER_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String REQUEST_BETTING_MONEY_MESSAGE = "%s의 배팅 금액은?";
    private static final String REQUEST_OPTION_MESSAGE = "%s는 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";

    public NamesDto requestPlayerName() {
        System.out.println(REQUEST_PLAYER_NAME_MESSAGE);
        return Mapper.toNamesDto(readLine());
    }

    public PlayerDto requestBettingMoney(String name) {
        System.out.println(String.format(REQUEST_BETTING_MONEY_MESSAGE, name));
        return Mapper.toPlayerDto(name, readLine());
    }

    public Option requestOption(String name) {
        System.out.println(String.format(REQUEST_OPTION_MESSAGE, name));
        Optional<Option> option = Option.findOption(readLine());

        if (!option.isPresent()) {
            throw new IllegalArgumentException("잘못된 옵션 입력값");
        }

        return option.get();
    }

}
