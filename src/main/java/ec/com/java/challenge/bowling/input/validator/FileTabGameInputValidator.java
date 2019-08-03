package ec.com.java.challenge.bowling.input.validator;

public class FileTabGameInputValidator implements IGameInputValidator {
    @Override
    public boolean validate(Object obj) {
        if(obj!=null) {
            String line = obj.toString();
            String[] lineSplit = line.trim().split("\\s+");
            if(lineSplit.length == 2) {
                if(lineSplit[1].toUpperCase().equals("F"))
                    return true;
                else {
                    int pins;
                    try {
                        pins = Integer.parseInt(lineSplit[1]);
                        if (pins >=0 && pins <=10)
                            return true;
                    } catch (NumberFormatException ex) {
                        return false;
                    }
                }
            }
        }
        return false;
    }
}
