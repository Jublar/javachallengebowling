package ec.com.java.challenge.bowling.util;

/**
 * <p>GameLineUtil class.</p>
 *
 * @author Jublar Garcia Ramos
 * @version 1.0
 */
public class GameLineUtil {

    /**
     * <p>playerName.</p>
     *
     * @param line a {@link java.lang.String} object.
     * @return a {@link java.lang.String} object.
     */
    public static String playerName(String line) {
        return lineSplitIndex(line, 0);
    }

    /**
     * <p>isFoul.</p>
     *
     * @param line a {@link java.lang.String} object.
     * @return a boolean.
     */
    public static boolean isFoul(String line) {
        return Constants.MSG_FOUL_PINFALL.equals(lineSplitIndex(line, 1));
    }

    /**
     * <p>pins.</p>
     *
     * @param line a {@link java.lang.String} object.
     * @return a int.
     */
    public static int pins(String line) {
        if(isFoul(line))
            return 0;
        try {
            return Integer.parseInt(lineSplitIndex(line, 1));
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

    private static String lineSplitIndex(String line, int index) {
        String[] lineSplit = splitLine(line);
        if (lineSplit.length > index) {
            return lineSplit[index];
        }
        return "";
    }

    private static String[] splitLine(String line) {
        if (line != null) {
            return line.trim().split("\\s+");
        }
        return new String[] {};
    }
}
