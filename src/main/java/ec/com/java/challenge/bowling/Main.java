package ec.com.java.challenge.bowling;

import ec.com.java.challenge.bowling.controller.IBowlingGameControler;
import ec.com.java.challenge.bowling.controller.TenPinBowlingGameControler;
import ec.com.java.challenge.bowling.util.GameInputType;
import ec.com.java.challenge.bowling.util.GameOutputType;

public class Main {
    public static void main(String... args) {
        IBowlingGameControler controller = new TenPinBowlingGameControler(GameInputType.FILE, GameOutputType.CONSOLE);
        controller.run();
    }
}
