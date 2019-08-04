package ec.com.java.challenge.bowling.input;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FileMockUtil {

    private final static String FILE_NAME = "input.txt";
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

    public static void writeToFile(List<String> lines) {
        BufferedWriter outputWriter = null;
        try {
            outputWriter = new BufferedWriter(new FileWriter(FILE_PATH));
            for (int i = 0; i < lines.size(); i++) {
                outputWriter.write(lines.get(i));
                outputWriter.newLine();
            }
            outputWriter.flush();
            outputWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeLinesInFile(int valid, int notValid) {
        FileMockUtil.createMockFile();
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < (valid + notValid); i++) {
            lines.add(i < valid ? generateValidLine() : generateNotValidLine());
        }
        FileMockUtil.writeToFile(lines);
    }

    private static String generateValidLine() {
        return String.format("player  %s", new Random().nextInt(11));
    }

    private static String generateNotValidLine() {
        return "this is an invalid line in file";
    }
}
