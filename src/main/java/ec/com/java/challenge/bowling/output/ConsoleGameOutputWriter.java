package ec.com.java.challenge.bowling.output;

import ec.com.java.challenge.bowling.game.BowlingFrame;
import ec.com.java.challenge.bowling.game.BowlingTurn;
import ec.com.java.challenge.bowling.game.IBowlingGame;
import ec.com.java.challenge.bowling.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * <p>ConsoleGameOutputWriter class.</p>
 *
 * @author Jublar Garcia Ramos
 * @version 1.0
 */
public class ConsoleGameOutputWriter implements IGameOutputWriter {

    private static final String SEPARATOR = "\t";

    /** {@inheritDoc} */
    @Override
    public void write(List<IBowlingGame> games) {
        System.out.println(headerLine());
        games.forEach(g -> gameLine(g).stream().forEach(l -> System.out.println(l)));
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
        score.forEach(s -> scoreLine.append("\t").append("\t").append(game.frameScoreSum(s + 1)));
        lines.add(scoreLine.toString());

        return lines;
    }

    private static String framePinFall(IBowlingGame game, int frameIndex) {
        BowlingFrame frame = game.getFrame(frameIndex);
        if (frame != null) {
            if (frameIndex < Constants.BONUS_FRAME_INDEXES)
                return normalFramePinFall(frame);
            else if (frameIndex == Constants.BONUS_FRAME_INDEXES)
                return bonusFramePinFall(frame);
        }
        return "";
    }

    private static String normalFramePinFall(BowlingFrame frame) {
        if (frame.isStrike())
            return String.format("%s%s%s", SEPARATOR, SEPARATOR, Constants.MSG_STRIKE_PINFALL);
        else if (frame.isSpare()) {
            String turn1 = turnValue(frame, 0);
            return String.format("%s%s%s%s", SEPARATOR, turn1, SEPARATOR, Constants.MSG_SPARE_PINFALL);
        } else {
            String turn1 = turnValue(frame, 0);
            String turn2 = turnValue(frame, 1);
            return String.format("%s%s%s%s", SEPARATOR, turn1, SEPARATOR, turn2);
        }
    }

    private static String bonusFramePinFall(BowlingFrame frame) {
        String turn1 = "", turn2 = "", turn3 = "";
        if (frame.getTurns().size() > 2)
            turn1 = Constants.MSG_STRIKE_PINFALL;
        else
            turn1 = turnValue(frame, 0);
        turn2 = frame.isSpare() ? Constants.MSG_SPARE_PINFALL : turnValue(frame, 1);
        turn3 = turnValue(frame, 2);
        return String.format("%s%s%s%s%s%s", SEPARATOR, turn1, SEPARATOR, turn2, SEPARATOR, turn3);
    }

    private static String turnValue(BowlingFrame frame, int turnIndex) {
        if (frame.getTurns() != null && frame.getTurns().size() > turnIndex) {
            BowlingTurn turn = frame.getTurns().get(turnIndex);
            if (turn.isFoul())
                return Constants.MSG_FOUL_PINFALL;
            else if (turn.getPins() == 10)
                return Constants.MSG_STRIKE_PINFALL;
            return String.valueOf(turn.getPins());
        }
        return "";
    }
}
