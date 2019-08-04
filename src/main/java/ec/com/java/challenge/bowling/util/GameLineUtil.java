package ec.com.java.challenge.bowling.util;

public class GameLineUtil {

    public static String playerName(String line) {
        String[] lineSplit = splitLine(line);
        if (lineSplit.length == 2) {
            return lineSplit[0];
        }
        return null;
    }

    public static int pins(String line) {
        String[] lineSplit = splitLine(line);
        if (lineSplit.length == 2) {
            if (lineSplit[1].toUpperCase().equals("F"))
                return 0;
            try {
                return Integer.parseInt(lineSplit[1]);
            } catch (NumberFormatException ex) {
                return -1;
            }
        }
        return -1;
    }

    private static String[] splitLine(String line) {
        if (line != null) {
            return line.trim().split("\\s+");
        }
        return new String[] {};
    }
}
