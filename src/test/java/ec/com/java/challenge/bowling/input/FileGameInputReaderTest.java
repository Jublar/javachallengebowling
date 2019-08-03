package ec.com.java.challenge.bowling.input;

import ec.com.java.challenge.bowling.input.validator.FileTabGameInputValidator;
import ec.com.java.challenge.bowling.input.validator.IGameInputValidator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FileGameInputReaderTest {
    private static IGameInputReader fileReader;
    private final static String FILE_NAME = "input.txt";
    private static String FILE_PATH = String.format("%s%s%s", System.getProperty("user.dir"), File.separator, FILE_NAME);

    @BeforeClass
    public static void init() {
        IGameInputValidator inputValidator = new FileTabGameInputValidator();
        fileReader = new FileGameInputReader(FILE_PATH, inputValidator);
    }

    @Test
    public void zeroLines() {
        createMockFile();
        List<String> lines = fileReader.read();
        Assert.assertEquals(lines.size(), 0);
        deleteMockFile();
    }

    @Test
    public void tenLinesOk() {
        int linesCant = 10;
        writeLinesInFile(linesCant, 0);
        List<String> lines = fileReader.read();
        Assert.assertEquals(lines.size(), linesCant);
        deleteMockFile();
    }

    @Test
    public void tenLinesOkOneBadLine() {
        writeLinesInFile(10, 1);
        List<String> lines = fileReader.read();
        Assert.assertEquals(lines.size(), 10);
        deleteMockFile();
    }

    @Test
    public void allBadLines() {
        writeLinesInFile(0, 5);
        List<String> lines = fileReader.read();
        Assert.assertEquals(lines.size(), 0);
        deleteMockFile();
    }

    private static void createMockFile() {
        deleteMockFile();
        File file = new File(FILE_PATH);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deleteMockFile() {
        File file = new File(FILE_PATH);
        file.delete();
    }

    private static void writeLinesInFile(int valid, int notValid) {
        createMockFile();
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < (valid + notValid); i++) {
            lines.add(i < valid ? generateValidLine() : generateNotValidLine());
        }
        writeToFile(lines);
    }

    private static void writeToFile(List<String> lines) {
        BufferedWriter outputWriter;
        try {
            outputWriter = new BufferedWriter(new FileWriter(FILE_PATH));
            for (String line : lines) {
                outputWriter.write(line);
                outputWriter.newLine();
            }
            outputWriter.flush();
            outputWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String generateValidLine() {
        return String.format("player  %s", new Random().nextInt(11));
    }

    private static String generateNotValidLine() {
        return "this is an invalid line in file";
    }
}
