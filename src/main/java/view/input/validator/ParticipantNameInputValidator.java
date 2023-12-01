package view.input.validator;

public class ParticipantNameInputValidator implements NotEmptyCheckable {
    public void validate(String userInput) {
        validateIsNotEmpty(userInput);
    }
}