package ec.com.java.challenge.bowling.game;

import ec.com.java.challenge.bowling.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TenPinBowlingGame implements IBowlingGame{

    private final List<BowlingFrame> frames;
    private final String playerName;

    public TenPinBowlingGame(String playerName) {
        frames = new ArrayList<>();
        frames.add(new BowlingFrame());
        this.playerName = playerName;
    }

    @Override
    public void roll(int pins) {
        roll(pins, false);
    }

    @Override
    public void roll(int pins, boolean isFoul) {
        BowlingFrame lastFrame = frames.get(frames.size() - 1);
        BowlingFrame currentFrame = lastFrame;
        boolean isBonusFrame = Constants.BONUS_FRAME_INDEXES == (frames.size() - 1);
        int turnsAllowed = (isBonusFrame && (currentFrame.isStrike() || currentFrame.isSpare())) ? Constants.TURN_PER_BONUS_FRAME : Constants.TURN_PER_FRAME;
        int pinsAllowed = !isBonusFrame ? Constants.MAX_PINS_NORMAL_FRAME : Constants.MAX_PINS_BONUS_FRAME;
        boolean pinsMoreThanMax = pinsAllowed < (currentFrame.getTurns().stream().mapToInt(BowlingTurn::getPins).sum() + pins);
        boolean turnAvailable = lastFrame.getTurns().size() < turnsAllowed && !pinsMoreThanMax;
        boolean frameAvailable = frames.size() < Constants.MAX_FRAMES;
        if(frameAvailable && !(lastFrame.getTurns().size() < turnsAllowed)) {
            currentFrame = new BowlingFrame();
            frames.add(currentFrame);
            turnAvailable = true;
        }
        if ((currentFrame.isStrike() || currentFrame.isSpare()) && frameAvailable) {
            BowlingFrame newFrame = new BowlingFrame();
            newFrame.addTurn(pins, isFoul);
            frames.add(newFrame);
        } else if (turnAvailable) {
            currentFrame.addTurn(pins, isFoul);
        } else {
            String msg = !pinsMoreThanMax ? String.format(Constants.MSG_PAYER_NO_MORE_TURN, this.playerName) :
                    String.format(Constants.MSG_MAXIMUM_PINS_ALLOWED,
                            currentFrame.getTurns().stream().mapToInt(BowlingTurn::getPins).sum(), pins, pinsAllowed);
            System.err.println(msg);
        }
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
        int nextFrameIndex = frameIndex == (Constants.MAX_FRAMES - 1)  ? frameIndex : frameIndex + 1;
        int nextShotIndex = frameIndex == (Constants.MAX_FRAMES - 1)  ? 1 : 0;
        if(frameIndex >= 0 && frameIndex < frames.size()) {
            BowlingFrame frame = frames.get(frameIndex);
            if(frame.isStrike()) {
                score += 10;
                nextShots = 2;
            } else if(frame.isSpare()) {
                score += 10;
                nextShots = 1;
            } else {
                score = frame.getTurns().stream().mapToInt(BowlingTurn::getPins).sum();
            }
            if(nextShots > 0) {
                score += shotRecursion(nextFrameIndex, nextShotIndex, nextShots);
            }
        }
        return score;
    }

    @Override
    public int frameScoreSum(int frameNum) {
        return IntStream.range(1, frameNum + 1).map(this::frameScore).sum();
    }

    private int shotRecursion(int frameIndex, int shotIndex, int nextShots) {
        int score = 0;
        if(frameIndex >= 0 && frameIndex < frames.size()) {
            BowlingFrame frame = frames.get(frameIndex);
            score += frame.getTurns().size() > shotIndex ? frame.getTurns().get(shotIndex).getPins() : 0;
            frameIndex = frame.getTurns().size() >= nextShots ? frameIndex : frameIndex + 1;
            shotIndex = frame.getTurns().size() >= nextShots ? shotIndex + 1 : 0;
        }
        nextShots--;
        if(nextShots > 0) {
            score += shotRecursion(frameIndex, shotIndex, nextShots);
        }
        return score;
    }

}
