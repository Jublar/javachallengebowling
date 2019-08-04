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

    @BeforeClass
    public static void init() {
        IGameInputValidator inputValidator = new FileTabGameInputValidator();
        fileReader = new FileGameInputReader(FileMockUtil.FILE_PATH, inputValidator);
    }

    @Test
    public void zeroLines() {
        FileMockUtil.createMockFile();
        List<String> lines = fileReader.read();
        Assert.assertEquals(lines.size(), 0);
        FileMockUtil.deleteMockFile();
    }

    @Test
    public void tenLinesOk() {
        int linesCant = 10;
        FileMockUtil.writeLinesInFile(linesCant, 0);
        List<String> lines = fileReader.read();
        Assert.assertEquals(lines.size(), linesCant);
        FileMockUtil.deleteMockFile();
    }

    @Test
    public void tenLinesOkOneBadLine() {
        FileMockUtil.writeLinesInFile(10, 1);
        List<String> lines = fileReader.read();
        Assert.assertEquals(lines.size(), 10);
        FileMockUtil.deleteMockFile();
    }

    @Test
    public void allBadLines() {
        FileMockUtil.writeLinesInFile(0, 5);
        List<String> lines = fileReader.read();
        Assert.assertEquals(lines.size(), 0);
        FileMockUtil.deleteMockFile();
    }
}
