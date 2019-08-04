package ec.com.java.challenge.bowling.util;

public final class Constants {
    public static final int MAX_FRAMES = 10;
    public static final int TURN_PER_FRAME = 2;
    public static final int TURN_PER_BONUS_FRAME = 3;
    public static final int BONUS_FRAME_INDEXES = 9;
    public static final int MAX_PINS_NORMAL_FRAME = 10;
    public static final int MAX_PINS_BONUS_FRAME = 30;
    public static final String MSG_PAYER_NO_MORE_TURN = "Player %s has no more turns available.";
    public static final String MSG_MAXIMUM_PINS_ALLOWED = "Total of pins is more than maximum pins allowed to roll out in this frame. %d + %d > %d.";
}
