package ec.com.java.challenge.bowling.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TenPinBowlingGame implements IBowlingGame{
    private static int MAX_FRAMES = 10;
    private static int TURN_PER_FRAME = 2;
    private static int TURN_PER_BONUS_FRAME = 3;
    private static int[] BONUS_FRAME_INDEXES = {9};
    private List<BowlingFrame> frames;
    private String playerName;

    public TenPinBowlingGame(String playerName) {
        frames = new ArrayList<>();
        frames.add(new BowlingFrame());
        this.playerName = playerName;
    }

    public void roll(int pins) {
        BowlingFrame currentFrame = (frames.size() == 0) ? new BowlingFrame() : frames.get(frames.size() - 1);
        boolean isBonusFrame = Arrays.asList(BONUS_FRAME_INDEXES).contains(frames.size() - 1);
        int turnsAllowed = isBonusFrame ? TURN_PER_BONUS_FRAME : TURN_PER_FRAME;
        boolean turnAvailable = currentFrame.turns.size() < turnsAllowed;
        boolean frameAvailable = frames.size() < MAX_FRAMES;
        if ((currentFrame.isStrike() || currentFrame.isSpare()) && frameAvailable) {
            BowlingFrame newFrame = new BowlingFrame();
            newFrame.addTurn(pins);
            frames.add(newFrame);
        } else if (turnAvailable) {
            currentFrame.addTurn(pins);
        } else
            System.err.println(String.format("Player %s has no more turn available.", this.playerName));
    }

    public int score() {
        return 0;
    }

    public String playerName() {
        return playerName;
    }

    @Override
    public int frameScore(int frameNumber) {
        int score = 0;
        int nextSteps = 0;
        if(frameNumber > 0 && frameNumber <= frames.size()) {
            BowlingFrame frame = frames.get(frameNumber - 1);
            if(frame.isStrike()) {
                score += 10;
                nextSteps = 2;
            } else if(frame.isSpare()) {
                score += 10;
                nextSteps = 1;
            } else {
                score = frame.turns.stream().mapToInt(t -> t.getPins()).sum();
            }
            if(nextSteps > 0) {
                score += frameRecursion(frameNumber, nextSteps);
            }
        }
        return score;
    }

    private int frameRecursion(int index, int nextSteps) {
        int score = 0;
        if(index >= 0 && index < frames.size()) {
            BowlingFrame frame = frames.get(index);
            if(frame.isStrike()) {
                score += 10;
                nextSteps = 1;
            } else if(frame.isSpare()) {
                score += 10;
            } else {
                score = frame.turns.stream().mapToInt(t -> t.getPins()).sum();
            }
            if(nextSteps > 0) {
                score += frameRecursion(index, nextSteps);
            }
        }
        return score;
    }
}
