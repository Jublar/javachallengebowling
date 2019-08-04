package ec.com.java.challenge.bowling.input;

import java.io.File;
import java.io.IOException;

public class FileMockUtil {

    public final static String FILE_NAME = "input.txt";
    public final static String FILE_PATH = String.format("%s%s%s", System.getProperty("user.dir"), File.separator, FILE_NAME);

    public static void createMockFile() {
        deleteMockFile();
        File file = new File(FileMockUtil.FILE_PATH);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteMockFile() {
        File file = new File(FileMockUtil.FILE_PATH);
        file.delete();
    }
}
