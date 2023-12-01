package view.input.validator;

public class ParticipantNamesInputValidator implements NotEmptyCheckable, ContainingDelimiterCheckable {
    public void validate(String userInput) {
        validateIsNotEmpty(userInput);
        validateContainsDelimiter(userInput);
        validateIsNotEndWithDelimiter(userInput);
    }
}
