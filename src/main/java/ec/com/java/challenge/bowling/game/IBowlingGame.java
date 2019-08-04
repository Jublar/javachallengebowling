package ec.com.java.challenge.bowling.game;

/**
 * <p>IBowlingGame interface.</p>
 *
 * @author Jublar Garcia Ramos
 * @version 1.0
 */
public interface IBowlingGame {
    /**
     * <p>roll.</p>
     *
     * @param pins a int.
     */
    void roll(int pins);
    /**
     * <p>roll.</p>
     *
     * @param pins a int.
     * @param isFoul a boolean.
     */
    void roll(int pins, boolean isFoul);
    /**
     * <p>score.</p>
     *
     * @return a int.
     */
    int score();
    /**
     * <p>playerName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String playerName();
    /**
     * <p>frameScore.</p>
     *
     * @param frame a int.
     * @return a int.
     */
    int frameScore(int frame);
    /**
     * <p>frameScoreSum.</p>
     *
     * @param frame a int.
     * @return a int.
     */
    int frameScoreSum(int frame);
    /**
     * <p>getFrame.</p>
     *
     * @param index a int.
     * @return a {@link ec.com.java.challenge.bowling.game.BowlingFrame} object.
     */
    BowlingFrame getFrame(int index);
}
