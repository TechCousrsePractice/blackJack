package view;

import static camp.nextstep.edu.missionutils.Console.readLine;

import dto.PlayersDto;
import util.Mapper;

public class InputView {
    private static final String REQUEST_PLAYER_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";

    public PlayersDto requestPlayerName() {
        System.out.println(REQUEST_PLAYER_NAME_MESSAGE);
        return Mapper.toPlayerDto(readLine());
    }
}
