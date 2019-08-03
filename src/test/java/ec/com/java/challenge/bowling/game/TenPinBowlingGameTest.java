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

    @Test
    public void addStrikeSpareAndSingle() {
        initGame();
        game.roll(10);
        game.roll(7);
        game.roll(3);
        game.roll(7);
        game.roll(2);
        Assert.assertEquals(game.frameScore(1), 20);
        Assert.assertEquals(game.frameScore(2), 17);
        Assert.assertEquals(game.frameScore(3), 9);
    }

    @Test
    public void addCompleteGameWithSpareBonus() {
        initGame();
        //frame 1
        game.roll(10);
        //frame 2
        game.roll(7);
        game.roll(3);
        //frame 3
        game.roll(7);
        game.roll(2);
        //frame 4
        game.roll(9);
        game.roll(1);
        //frame 5
        game.roll(10);
        //frame 6
        game.roll(10);
        //frame 7
        game.roll(10);
        //frame 8
        game.roll(2);
        game.roll(3);
        //frame 9
        game.roll(6);
        game.roll(4);
        //frame 10
        game.roll(7);
        game.roll(3);
        game.roll(3);

        Assert.assertEquals(game.frameScore(1), 20);
        Assert.assertEquals(game.frameScore(2), 17);
        Assert.assertEquals(game.frameScore(3), 9);
        Assert.assertEquals(game.frameScore(4), 20);
        Assert.assertEquals(game.frameScore(5), 30);
        Assert.assertEquals(game.frameScore(6), 22);
        Assert.assertEquals(game.frameScore(7), 15);
        Assert.assertEquals(game.frameScore(8), 5);
        Assert.assertEquals(game.frameScore(9), 17);
        Assert.assertEquals(game.frameScore(10), 13);

    }

    @Test
    public void addCompleteGameWithStrikeBonus() {
        initGame();
        rollHoleGame();

        Assert.assertEquals(game.frameScore(1), 20);
        Assert.assertEquals(game.frameScore(2), 17);
        Assert.assertEquals(game.frameScore(3), 9);
        Assert.assertEquals(game.frameScore(4), 20);
        Assert.assertEquals(game.frameScore(5), 30);
        Assert.assertEquals(game.frameScore(6), 22);
        Assert.assertEquals(game.frameScore(7), 15);
        Assert.assertEquals(game.frameScore(8), 5);
        Assert.assertEquals(game.frameScore(9), 20);
        Assert.assertEquals(game.frameScore(10), 16);
    }

    @Test
    public void addGameGetSumScoreMiddle() {
        initGame();
        rollHoleGame();
        Assert.assertEquals(game.frameScoreSum(10), 174);
    }

    private static void rollHoleGame() {
        //frame 1
        game.roll(10);
        //frame 2
        game.roll(7);
        game.roll(3);
        //frame 3
        game.roll(7);
        game.roll(2);
        //frame 4
        game.roll(9);
        game.roll(1);
        //frame 5
        game.roll(10);
        //frame 6
        game.roll(10);
        //frame 7
        game.roll(10);
        //frame 8
        game.roll(2);
        game.roll(3);
        //frame 9
        game.roll(6);
        game.roll(4);
        //frame 10
        game.roll(10);
        game.roll(3);
        game.roll(3);
    }
}
