package ec.com.java.challenge.bowling.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class TenPinBowlingGame implements IBowlingGame{
    private static int MAX_FRAMES = 10;
    private static int TURN_PER_FRAME = 2;
    private static int TURN_PER_BONUS_FRAME = 3;
    private static int BONUS_FRAME_INDEXES = 9;
    private List<BowlingFrame> frames;
    private String playerName;

    public TenPinBowlingGame(String playerName) {
        frames = new ArrayList<>();
        frames.add(new BowlingFrame());
        this.playerName = playerName;
    }

    @Override
    public void roll(int pins) {
        BowlingFrame lastFrame = frames.get(frames.size() - 1);
        BowlingFrame currentFrame = lastFrame;
        boolean isBonusFrame = BONUS_FRAME_INDEXES == (frames.size() - 1);
        int turnsAllowed = (isBonusFrame && (currentFrame.isStrike() || currentFrame.isSpare())) ? TURN_PER_BONUS_FRAME : TURN_PER_FRAME;
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

    @Override
    public int score() {
        return 0;
    }

    @Override
    public String playerName() {
        return playerName;
    }

    @Override
    public int frameScore(int frameNumber) {
        int score = 0;
        int nextShots = 0;
        int frameIndex = frameNumber - 1;
        int nextFrameIndex = frameIndex == (MAX_FRAMES - 1)  ? frameIndex : frameIndex + 1;
        int nextShotIndex = frameIndex == (MAX_FRAMES - 1)  ? 1 : 0;
        if(frameIndex >= 0 && frameIndex < frames.size()) {
            BowlingFrame frame = frames.get(frameIndex);
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
                score += shotRecursion(nextFrameIndex, nextShotIndex, nextShots);
            }
        }
        return score;
    }

    @Override
    public int frameScoreSum(int frameNum) {
        int score = IntStream.range(1, frameNum + 1).map(this::frameScore).sum();
        return score;
    }

    private int shotRecursion(int frameIndex, int shotIndex, int nextShots) {
        int score = 0;
        if(frameIndex >= 0 && frameIndex < frames.size()) {
            BowlingFrame frame = frames.get(frameIndex);
            score += frame.turns.size() > shotIndex ? frame.turns.get(shotIndex).getPins() : 0;
            frameIndex = frame.turns.size() >= nextShots ? frameIndex : frameIndex + 1;
            shotIndex = frame.turns.size() >= nextShots ? shotIndex + 1 : 0;
        }
        nextShots--;
        if(nextShots > 0) {
            score += shotRecursion(frameIndex, shotIndex, nextShots);
        }
        return score;
    }

}
