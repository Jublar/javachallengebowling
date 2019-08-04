package ec.com.java.challenge.bowling.parser;

import ec.com.java.challenge.bowling.game.IBowlingGame;

import java.util.List;

/**
 * <p>IBowlingGameParser interface.</p>
 *
 * @author Jublar Garcia Ramos
 * @version 1.0
 */
public interface IBowlingGameParser {
    /**
     * <p>createSingle.</p>
     *
     * @return a {@link ec.com.java.challenge.bowling.game.IBowlingGame} object.
     */
    IBowlingGame createSingle();
    /**
     * <p>createAll.</p>
     *
     * @return a {@link java.util.List} object.
     */
    List<IBowlingGame> createAll();
}
