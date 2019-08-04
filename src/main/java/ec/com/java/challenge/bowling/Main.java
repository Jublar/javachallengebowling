package ec.com.java.challenge.bowling;

import ec.com.java.challenge.bowling.controller.IBowlingGameController;
import ec.com.java.challenge.bowling.controller.TenPinBowlingGameController;
import ec.com.java.challenge.bowling.util.GameInputType;
import ec.com.java.challenge.bowling.util.GameOutputType;

public class Main {
    public static void main(String... args) {
        IBowlingGameController controller = new TenPinBowlingGameController(GameInputType.FILE, GameOutputType.CONSOLE);
        controller.run();
    }
}