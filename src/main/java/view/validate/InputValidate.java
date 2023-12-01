package view.validate;

import static java.lang.Integer.parseInt;
import static view.constants.InputConstants.ENTER_SEPARATION;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import view.constants.ErrorMessage;

public class InputValidate {
    private static final Pattern NON_ALPHABETIC = Pattern.compile("[a-zA-Z가-힣]+");
    private InputValidate() {}

    public static InputValidate create() {
        return new InputValidate();
    }

    public List<String> IsNames(String enterNames) {
        List<String> nonAlphabeticParts = new ArrayList<>();

        Arrays.stream(enterNames.split(ENTER_SEPARATION))
                .map(NON_ALPHABETIC::matcher)
                .forEach(matcher -> {
                    while (matcher.find()) {
                        String group = matcher.group();
                        nonAlphabeticParts.add(group);
                    }
                });

        return nonAlphabeticParts;
    }

    public int isAmount(String enterAmount) {
        try{
            return parseInt(enterAmount);
        }
        catch (NumberFormatException e){
            throw new IllegalArgumentException(ErrorMessage.IS_INTEGER.getMessage());
        }
    }

    public String isYesOrNo(String enterYesOrNo) {
        if(enterYesOrNo.equals("y") || enterYesOrNo.equals("n")){
            return enterYesOrNo;
        }
        throw new IllegalArgumentException(ErrorMessage.IS_YES_OR_NO.getMessage());
    }
}
