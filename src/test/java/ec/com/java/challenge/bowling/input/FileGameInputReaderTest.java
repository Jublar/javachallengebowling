package ec.com.java.challenge.bowling.input;

import ec.com.java.challenge.bowling.input.validator.FileTabGameInputValidator;
import ec.com.java.challenge.bowling.input.validator.IGameInputValidator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileGameInputReaderTest {
    private static IGameInputReader fileReader;
    private static IGameInputValidator inputValidator;
    private final static String FILE_NAME = "input.txt";
    public static String FILE_PATH = String.format("%s%s%s", System.getProperty("user.dir"), File.separator, FILE_NAME);

    @BeforeClass
    public static void init() {
        inputValidator = new FileTabGameInputValidator();
        fileReader = new FileGameInputReader(FILE_PATH, inputValidator);
    }

    @Test
    public void zeroLines() {
        createMockFile();
        List<String> lines = fileReader.read();
        Assert.assertEquals(lines.size(), 0);
        deleteMockFile();
    }

    public static void createMockFile() {
        deleteMockFile();
        File file = new File(FILE_PATH);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteMockFile() {
        File file = new File(FILE_PATH);
        file.delete();
    }
}
