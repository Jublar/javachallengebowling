package ec.com.java.challenge.bowling.game;

public class BowlingTurn {

    private int pins;
    private boolean isFoul;

    public void setPins(int pins) {
        this.pins = pins;
    }

    public int getPins() {
        return pins;
    }

    public void setFoul(boolean foul) {
        isFoul = foul;
    }

    public boolean isFoul() {
        return isFoul;
    }
}
