package view.input.parser;

import static view.input.constant.InputSymbolConstant.BLANK;
import static view.input.constant.InputSymbolConstant.VOID;

public class InputParser {
    // 각 Input Validator 선언.

    // 공백 제거 메서드 ( Input 값의 모든 공백 제거 )
    // userInput = removeBlank(userInput); 형식으로 사용하면 됨.
    // BLANK와 VOID는 InputSymbolConstant 에 enum 값으로 저장할 것.
    private String removeBlank(String userInput) {
        if (userInput.contains(BLANK.getSymbol())) {
            userInput = userInput.replace(BLANK.getSymbol(), VOID.getSymbol());
        }
        return userInput;
    }
}