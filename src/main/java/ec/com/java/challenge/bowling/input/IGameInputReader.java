package ec.com.java.challenge.bowling.input;

import java.util.List;

/**
 * API to read bowling game input.
 *
 * @author Jublar Garcia Ramos
 * @version 1.0
 */
public interface IGameInputReader {

    /**
     * Method to read input.
     *
     * @return List of lines read from input.
     */
    List<String> read();
}
