package ec.com.java.challenge.bowling.parser;

import ec.com.java.challenge.bowling.exception.RollValidationException;
import ec.com.java.challenge.bowling.game.IBowlingGame;
import ec.com.java.challenge.bowling.input.FileGameInputReader;
import ec.com.java.challenge.bowling.input.FileMockUtil;
import ec.com.java.challenge.bowling.input.IGameInputReader;
import ec.com.java.challenge.bowling.input.validator.FileTabGameInputValidator;
import ec.com.java.challenge.bowling.input.validator.IGameInputValidator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class TenPinBowlingGameLineParserTest {
    private static  IBowlingGameParser gameParser;
    private static IGameInputReader fileReader;

    @BeforeClass
    public static void init() throws IOException {
        FileMockUtil.createMockFile();
        IGameInputValidator inputValidator = new FileTabGameInputValidator();
        fileReader = new FileGameInputReader(FileMockUtil.FILE_PATH, inputValidator);
    }

    private void initParser(List<String> lines) {
        gameParser = new TenPinBowlingGameLineParser(lines);
    }

    @Test
    public void testBasicGameCreation() {
        FileMockUtil.writeLinesInFile(10, 0, 5);
        List<String> lines = fileReader.read();
        initParser(lines);
        FileMockUtil.deleteMockFile();
        List<IBowlingGame> games = gameParser.createAll();
        Assert.assertEquals(games.size(), 1);
    }

    @Test(expected = RollValidationException.class)
    public void testBasicGameCreationException() {
        FileMockUtil.writeFixedValueLinesInFile(10, 6);
        List<String> lines = fileReader.read();
        initParser(lines);
        FileMockUtil.deleteMockFile();
        List<IBowlingGame> games = gameParser.createAll();
        Assert.assertEquals(games.size(), 1);
    }
}
