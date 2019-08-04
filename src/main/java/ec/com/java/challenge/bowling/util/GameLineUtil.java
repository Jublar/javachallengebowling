package ec.com.java.challenge.bowling.util;

public class GameLineUtil {

    public static String playerName(String line) {
        return lineSplitIndex(line, 0);
    }

    public static boolean isFoul(String line) {
        return "F".equals(lineSplitIndex(line, 1));
    }

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
        return null;
    }

    private static String[] splitLine(String line) {
        if (line != null) {
            return line.trim().split("\\s+");
        }
        return new String[] {};
    }
}
