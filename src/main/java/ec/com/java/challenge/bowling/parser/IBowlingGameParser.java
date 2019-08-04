package ec.com.java.challenge.bowling.parser;

import ec.com.java.challenge.bowling.game.IBowlingGame;

import java.util.List;

public interface IBowlingGameParser {
    IBowlingGame createSingle();
    List<IBowlingGame> createAll();
}
