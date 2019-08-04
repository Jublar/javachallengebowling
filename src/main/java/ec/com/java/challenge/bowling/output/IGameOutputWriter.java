package ec.com.java.challenge.bowling.output;

import ec.com.java.challenge.bowling.game.IBowlingGame;

import java.util.List;

public interface IGameOutputWriter {
    void write(List<IBowlingGame> games);
}
