package ec.com.java.challenge.bowling.game;

public interface IBowlingGame {
    void roll(int pins);
    int score();
    String playerName();
    int frameScore(int frame);
}
