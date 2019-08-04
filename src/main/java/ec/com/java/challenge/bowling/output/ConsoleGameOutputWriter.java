package ec.com.java.challenge.bowling.output;

import ec.com.java.challenge.bowling.game.BowlingFrame;
import ec.com.java.challenge.bowling.game.BowlingTurn;
import ec.com.java.challenge.bowling.game.IBowlingGame;
import ec.com.java.challenge.bowling.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class ConsoleGameOutputWriter implements IGameOutputWriter {

    private static final String SEPARATOR = "\t";

    @Override
    public void write(List<IBowlingGame> games) {
        System.out.println(headerLine());
        games.stream().forEach(g->gameLine(g).stream().forEach(l -> System.out.println(l)));
    }

    private static String headerLine() {
        StringBuilder line = new StringBuilder();
        line.append(Constants.MSG_FRAME);
        IntStream stream1 = IntStream.range(0, Constants.MAX_FRAMES);
        stream1.forEach(s -> line.append("\t").append("\t").append(s + 1));
        return line.toString();
    }

    private static List<String> gameLine(IBowlingGame game) {
        List<String> lines = new ArrayList<>();
        lines.add(game.playerName());
        StringBuilder line = new StringBuilder();
        line.append(Constants.MSG_PINFALLS);
        IntStream pinFalls = IntStream.range(0, Constants.MAX_FRAMES);
        IntStream score = IntStream.range(0, Constants.MAX_FRAMES);
        pinFalls.forEach(p -> line.append(framePinFall(game, p)));
        lines.add(line.toString());
        StringBuilder scoreLine = new StringBuilder();
        scoreLine.append(Constants.MSG_SCORE);
        score.forEach(s -> scoreLine.append("\t").append("\t").append(String.valueOf(game.frameScore(s))));
        lines.add(scoreLine.toString());

        return lines;
    }

    private static String framePinFall(IBowlingGame game, int frameIndex) {
        BowlingFrame frame = game.getFrame(frameIndex);
        if(frame != null) {
            if(frame.isStrike())
                return String.format("%s%s%s", SEPARATOR, SEPARATOR, Constants.MSG_STRIKE_PINFALL);
            else if(frame.isSpare()) {
                String turn1 = turnValue(frame, 0);
                return String.format("%s%s%s%s", SEPARATOR, turn1, SEPARATOR, Constants.MSG_SPARE_PINFALL);
            }
            else {
                String turn1 = turnValue(frame, 0);
                String turn2 = turnValue(frame, 1);
                return String.format("%s%s%s%s", SEPARATOR,turn1, SEPARATOR, turn2);
            }
        }
        return "";
    }

    private static String frameScore(IBowlingGame game, int frameIndex) {
        return String.valueOf(game.frameScore(frameIndex));
    }
    private static String turnValue(BowlingFrame frame, int turnIndex) {
        if(frame.getTurns() != null && frame.getTurns().size() > turnIndex) {
            BowlingTurn turn = frame.getTurns().get(turnIndex);
            return turn.isFoul() ? Constants.MSG_FOUL_PINFALL : String.valueOf(turn.getPins());
        }
        return "";
    }
}
