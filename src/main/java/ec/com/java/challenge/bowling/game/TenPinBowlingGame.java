package ec.com.java.challenge.bowling.game;

import java.util.ArrayList;
import java.util.List;

public class TenPinBowlingGame implements IBowlingGame{

    private List<BowlingFrame> frames;
    private String playerName;

    public TenPinBowlingGame(String playerName) {
        frames = new ArrayList<>();
        this.playerName = playerName;
    }

    public void roll(int pins) {

    }

    public int score() {
        return 0;
    }

    public String playerName() {
        return playerName;
    }
}
