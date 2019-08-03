package ec.com.java.challenge.bowling.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

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
        BowlingFrame lastFrame = frames.get(frames.size() - 1);
        BowlingFrame currentFrame = lastFrame;
        boolean isBonusFrame = IntStream.of(BONUS_FRAME_INDEXES).anyMatch(i-> i== (frames.size() - 1));
        int turnsAllowed = isBonusFrame ? TURN_PER_BONUS_FRAME : TURN_PER_FRAME;
        boolean turnAvailable = lastFrame.turns.size() < turnsAllowed;
        boolean frameAvailable = frames.size() < MAX_FRAMES;
        if(frameAvailable && !(lastFrame.turns.size() < turnsAllowed)) {
            currentFrame = new BowlingFrame();
            frames.add(currentFrame);
            turnAvailable = true;
        }
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
        int nextShots = 0;
        if(frameNumber > 0 && frameNumber <= frames.size()) {
            BowlingFrame frame = frames.get(frameNumber - 1);
            if(frame.isStrike()) {
                score += 10;
                nextShots = 2;
            } else if(frame.isSpare()) {
                score += 10;
                nextShots = 1;
            } else {
                score = frame.turns.stream().mapToInt(t -> t.getPins()).sum();
            }
            if(nextShots > 0) {
                score += shotRecursion(frameNumber, 0, nextShots);
            }
        }
        return score;
    }

    private int shotRecursion(int frameIndex, int shotIndex, int nextShots) {
        int score = 0;
        if(frameIndex >= 0 && frameIndex < frames.size()) {
            BowlingFrame frame = frames.get(frameIndex);
            if(frame.isStrike()) {
                score += 10;
                frameIndex++;
                shotIndex = 0;
            } else {
                score += frame.turns.size() > shotIndex ? frame.turns.get(shotIndex).getPins() : 0;
                shotIndex++;
            }
            nextShots--;
            if(nextShots > 0) {
                score += shotRecursion(frameIndex, shotIndex, nextShots);
            }
        }
        return score;
    }
}
