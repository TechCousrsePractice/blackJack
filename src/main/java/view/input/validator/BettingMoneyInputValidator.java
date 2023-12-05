package view.input.validator;

public class BettingMoneyInputValidator implements NotEmptyCheckable, PositiveNumericCheckable {
    public void validate(String userInput) {
        validateIsNotEmpty(userInput);
        validateIsNumeric(userInput);
        validateIsPositive(userInput);
    }
}
