package ec.com.java.challenge.bowling.output;

import ec.com.java.challenge.bowling.game.IBowlingGame;

import java.util.List;

/**
 * <p>IGameOutputWriter interface.</p>
 *
 * @author Jublar Garcia Ramos
 * @version 1.0
 */
public interface IGameOutputWriter<T> {
    /**
     * <p>write.</p>
     *
     * @param games a {@link java.util.List} object.
     */
    void write(List<IBowlingGame> games);
    T getWriteResult();
}
