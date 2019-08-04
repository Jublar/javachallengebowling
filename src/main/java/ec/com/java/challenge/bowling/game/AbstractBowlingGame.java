package ec.com.java.challenge.bowling.game;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>AbstractBowlingGame class.</p>
 *
 * @author Jublar Garcia Ramos
 * @version 1.0
 */
abstract class AbstractBowlingGame implements IBowlingGame {
    final List<BowlingFrame> frames;
    final String playerName;

    /**
     * <p>Constructor for AbstractBowlingGame.</p>
     *
     * @param playerName a {@link java.lang.String} object.
     */
    AbstractBowlingGame(String playerName) {
        frames = new ArrayList<>();
        frames.add(new BowlingFrame());
        this.playerName = playerName;
    }
}
