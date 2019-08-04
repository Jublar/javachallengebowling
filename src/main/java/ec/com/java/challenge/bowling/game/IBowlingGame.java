package ec.com.java.challenge.bowling.game;

public interface IBowlingGame {
    void roll(int pins);
    void roll(int pins, boolean isFoul);
    int score();
    String playerName();
    int frameScore(int frame);
    int frameScoreSum(int frame);
}
