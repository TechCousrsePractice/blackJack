package view.input.parser;

import static view.input.constant.InputSymbolConstant.PARTICIPANT_NAME_DELIMITER;

import java.util.Arrays;
import java.util.List;
import view.input.validator.ParticipantNameInputValidator;
import view.input.validator.ParticipantNamesInputValidator;

public class InputParser {
    // 각 Input Validator 선언.
    private final ParticipantNamesInputValidator participantNamesInputValidator;
    private final ParticipantNameInputValidator participantNameInputValidator;

    public InputParser() {
        this.participantNamesInputValidator = new ParticipantNamesInputValidator();
        this.participantNameInputValidator = new ParticipantNameInputValidator();
    }


    public List<String> parseToParticipantNames(String userInput) {
        userInput = removeBlank(userInput);
        participantNamesInputValidator.validate(userInput);
        validateEachParticipantName(userInput);
        return parseToStringsTrimmed(userInput);
    }

    private List<String> parseToStringsTrimmed(String userInput) {
        return Arrays.stream(userInput.split(PARTICIPANT_NAME_DELIMITER.getSymbol()))
                .map(this::removeBlank)
                .toList();
    }

    private void validateEachParticipantName(String userInput) {
        Arrays.stream(userInput.split(PARTICIPANT_NAME_DELIMITER.getSymbol()))
                .forEach(participantNameInputValidator::validate);
    }
    
    private String removeBlank(String userInput) {
        return userInput.trim();
    }
}