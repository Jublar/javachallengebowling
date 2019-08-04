package ec.com.java.challenge.bowling.game;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>BowlingFrame class.</p>
 *
 * @author Jublar Garcia Ramos
 * @version 1.0
 */
public class BowlingFrame {

    private final List<BowlingTurn> turns;

    {
        turns = new ArrayList<>();
    }

    /**
     * <p>isStrike.</p>
     *
     * @return a boolean.
     */
    public boolean isStrike() {
        return turns != null && turns.size() > 0 && turns.get(0).getPins() == 10;
    }

    /**
     * <p>isSpare.</p>
     *
     * @return a boolean.
     */
    public boolean isSpare() {
        return turns != null && turns.size() > 1 && (turns.get(0).getPins() + turns.get(1).getPins()) == 10;
    }

    void addTurn(int pins, boolean isFoul) {
        BowlingTurn newTurn = new BowlingTurn();
        newTurn.setFoul(isFoul);
        newTurn.setPins(pins);
        turns.add(newTurn);
    }

    /**
     * <p>Getter for the field <code>turns</code>.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<BowlingTurn> getTurns() {
        return turns;
    }
}
