package ec.com.java.challenge.bowling;

import ec.com.java.challenge.bowling.controller.IBowlingGameController;
import ec.com.java.challenge.bowling.controller.TenPinBowlingGameController;
import ec.com.java.challenge.bowling.exception.InputValidationException;
import ec.com.java.challenge.bowling.exception.RollValidationException;
import ec.com.java.challenge.bowling.util.GameInputType;
import ec.com.java.challenge.bowling.util.GameOutputType;

/**
 * <p>Main class.</p>
 *
 * @author Jublar Garcia Ramos
 * @version 1.0
 */
public class Main {
    /**
     * <p>main.</p>
     *
     * @param args a {@link java.lang.String} object.
     */
    public static void main(String... args) {
        try {
            String fileName = "";
            if(args.length>0) {
                fileName = args[0];
            }
            IBowlingGameController controller = new TenPinBowlingGameController(GameInputType.FILE, GameOutputType.CONSOLE, fileName);
            controller.run();
        } catch (InputValidationException | RollValidationException e) {
            System.err.println(e.getMessage());
        }
    }
}
