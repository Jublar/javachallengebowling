package ec.com.java.challenge.bowling.game;

import org.junit.Assert;
import org.junit.Test;

public class TenPinBowlingGameTest {

    private static IBowlingGame game;
    private static String PLAYER_NAME = "TestPlayer";

    private static void initGame() {
        game = new TenPinBowlingGame(PLAYER_NAME);
    }

    @Test
    public void addSingleRoll() {
        initGame();
        game.roll(5);
        Assert.assertEquals(game.frameScore(1), 5);
    }

    @Test
    public void addStrikeAndSingle() {
        initGame();
        game.roll(10);
        game.roll(5);
        Assert.assertEquals(game.frameScore(1), 15);
        Assert.assertEquals(game.frameScore(2), 5);
    }
}
