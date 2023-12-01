package view.input.parser;

import static view.input.constant.InputSymbolConstant.BLANK;
import static view.input.constant.InputSymbolConstant.PARTICIPANT_NAME_DELIMITER;
import static view.input.constant.InputSymbolConstant.VOID;

import java.util.Arrays;
import java.util.List;
import view.input.validator.BettingMoneyInputValidator;
import view.input.validator.ParticipantNameInputValidator;
import view.input.validator.ParticipantNamesInputValidator;

public class InputParser {
    // 각 Input Validator 선언.
    private final ParticipantNamesInputValidator participantNamesInputValidator;
    private final ParticipantNameInputValidator participantNameInputValidator;
    private final BettingMoneyInputValidator bettingMoneyInputValidator;

    public InputParser() {
        this.participantNamesInputValidator = new ParticipantNamesInputValidator();
        this.participantNameInputValidator = new ParticipantNameInputValidator();
        this.bettingMoneyInputValidator = new BettingMoneyInputValidator();
    }


    public List<String> parseToParticipantNames(String userInput) {
        userInput = trimBlank(userInput);
        participantNamesInputValidator.validate(userInput);
        validateEachParticipantName(userInput);
        return parseToStringsTrimmed(userInput);
    }

    public double parseToBettingMoney(String userInput) {
        userInput = removeBlank(userInput);
        bettingMoneyInputValidator.validate(userInput);
        return parseToDouble(userInput);
    }

    private List<String> parseToStringsTrimmed(String userInput) {
        return Arrays.stream(userInput.split(PARTICIPANT_NAME_DELIMITER.getSymbol()))
                .map(this::trimBlank)
                .toList();
    }

    private void validateEachParticipantName(String userInput) {
        Arrays.stream(userInput.split(PARTICIPANT_NAME_DELIMITER.getSymbol()))
                .forEach(participantNameInputValidator::validate);
    }

    private String trimBlank(String userInput) {
        return userInput.trim();
    }

    private String removeBlank(String userInput) {
        if (userInput.contains(BLANK.getSymbol())) {
            userInput = userInput.replace(BLANK.getSymbol(), VOID.getSymbol());
        }
        return userInput;
    }

    private double parseToDouble(String userInput) {
        return Double.parseDouble(userInput);
    }
}