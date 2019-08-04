package ec.com.java.challenge.bowling.output;

import ec.com.java.challenge.bowling.game.IBowlingGame;
import ec.com.java.challenge.bowling.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class ConsoleGameOutputWriter implements IGameOutputWriter {

    @Override
    public void write(List<IBowlingGame> games) {
        System.out.println(headerLine());
    }

    private static String headerLine() {
        StringBuilder line = new StringBuilder();
        line.append(Constants.MSG_FRAME);
        IntStream stream1 = IntStream.range(1, Constants.MAX_FRAMES + 1);
        stream1.forEach(s -> line.append("\t").append("\t").append(s));
        return line.toString();
    }
}
