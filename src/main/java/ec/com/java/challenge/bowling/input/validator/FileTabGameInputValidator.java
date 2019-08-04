package ec.com.java.challenge.bowling.input.validator;

import ec.com.java.challenge.bowling.util.GameLineUtil;

public class FileTabGameInputValidator implements IGameInputValidator<String> {

    @Override
    public boolean validate(String input) {
        if (input != null) {
            int pins = GameLineUtil.pins(input);
            return pins >= 0 && pins <= 10;
        }
        return false;
    }
}
