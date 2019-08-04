package ec.com.java.challenge.bowling.parser;

import ec.com.java.challenge.bowling.game.IBowlingGame;

import java.util.List;

public class TenPinBowlingGameLineParser implements IBowlingGameParser{
    private List<String> lines;

    public TenPinBowlingGameLineParser(List<String> lines) {
        this.lines = lines;
    }

    @Override
    public IBowlingGame createSingle() {
        return null;
    }

    @Override
    public List<IBowlingGame> createAll() {
        return null;
    }
}
