package ec.com.java.challenge.bowling.game;

import java.util.ArrayList;
import java.util.List;

public class BowlingFrame {

    private final List<BowlingTurn> turns;

    {
        turns = new ArrayList<>();
    }

    public boolean isStrike() {
        return turns != null && turns.size() > 0 && turns.get(0).getPins() == 10;
    }

    public boolean isSpare() {
        return turns != null && turns.size() > 1 && (turns.get(0).getPins() + turns.get(1).getPins()) == 10;
    }

    void addTurn(int pins, boolean isFoul) {
        BowlingTurn newTurn = new BowlingTurn();
        newTurn.setFoul(isFoul);
        newTurn.setPins(pins);
        turns.add(newTurn);
    }

    public List<BowlingTurn> getTurns() {
        return turns;
    }
}
