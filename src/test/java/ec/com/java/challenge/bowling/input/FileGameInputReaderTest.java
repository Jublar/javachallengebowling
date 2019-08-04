package ec.com.java.challenge.bowling.input;

import ec.com.java.challenge.bowling.input.validator.FileTabGameInputValidator;
import ec.com.java.challenge.bowling.input.validator.IGameInputValidator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class FileGameInputReaderTest {
    private static IGameInputReader fileReader;

    @Before
    public void init() throws IOException {
        IGameInputValidator inputValidator = new FileTabGameInputValidator();
        FileMockUtil.createMockFile();
        fileReader = new FileGameInputReader(FileMockUtil.FILE_PATH, inputValidator);
    }

    @After
    public void after() {
        FileMockUtil.deleteMockFile();
    }

    @Test
    public void zeroLines() {
        FileMockUtil.createMockFile();
        List<String> lines = fileReader.read();
        Assert.assertEquals(lines.size(), 0);
    }

    @Test
    public void tenLinesOk() {
        int linesCant = 10;
        FileMockUtil.writeLinesInFile(linesCant, 0);
        List<String> lines = fileReader.read();
        Assert.assertEquals(lines.size(), linesCant);
    }

    @Test
    public void tenLinesOkOneBadLine() {
        FileMockUtil.writeLinesInFile(10, 1);
        List<String> lines = fileReader.read();
        Assert.assertEquals(lines.size(), 10);
    }

    @Test
    public void allBadLines() {
        FileMockUtil.writeLinesInFile(0, 5);
        List<String> lines = fileReader.read();
        Assert.assertEquals(lines.size(), 0);
    }
}
