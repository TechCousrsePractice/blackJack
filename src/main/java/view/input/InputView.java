package view.input;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import view.input.parser.InputParser;

public class InputView {
    private final InputParser inputParser;

    public InputView() {
        this.inputParser = new InputParser();
    }

    public List<String> getParticipantNames() {
        return inputParser.parseToParticipantNames(readLine());
    }

    public String readLine() {
        return Console.readLine();
    }
}