package ec.com.java.challenge.bowling.game;

/**
 * <p>BowlingTurn class.</p>
 *
 * @author Jublar Garcia Ramos
 * @version 1.0
 */
public class BowlingTurn {

    private int pins;
    private boolean isFoul;

    /**
     * <p>Setter for the field <code>pins</code>.</p>
     *
     * @param pins a int.
     */
    public void setPins(int pins) {
        this.pins = pins;
    }

    /**
     * <p>Getter for the field <code>pins</code>.</p>
     *
     * @return a int.
     */
    public int getPins() {
        return pins;
    }

    /**
     * <p>setFoul.</p>
     *
     * @param foul a boolean.
     */
    public void setFoul(boolean foul) {
        isFoul = foul;
    }

    /**
     * <p>isFoul.</p>
     *
     * @return a boolean.
     */
    public boolean isFoul() {
        return isFoul;
    }
}
