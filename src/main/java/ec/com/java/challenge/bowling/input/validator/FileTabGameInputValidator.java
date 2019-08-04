package ec.com.java.challenge.bowling.input.validator;

import ec.com.java.challenge.bowling.util.GameLineUtil;

public class FileTabGameInputValidator implements IGameInputValidator {
    @Override
    public boolean validate(Object obj) {
        if(obj!=null) {
            String line = obj.toString();
            int pins = GameLineUtil.pins(line);
            return pins >= 0 && pins <= 10;
        }
        return false;
    }
}
