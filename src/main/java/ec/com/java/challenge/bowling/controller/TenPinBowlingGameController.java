package ec.com.java.challenge.bowling.controller;

import ec.com.java.challenge.bowling.game.IBowlingGame;
import ec.com.java.challenge.bowling.input.FileGameInputReader;
import ec.com.java.challenge.bowling.input.IGameInputReader;
import ec.com.java.challenge.bowling.input.validator.FileTabGameInputValidator;
import ec.com.java.challenge.bowling.output.ConsoleGameOutputWriter;
import ec.com.java.challenge.bowling.output.IGameOutputWriter;
import ec.com.java.challenge.bowling.parser.IBowlingGameParser;
import ec.com.java.challenge.bowling.parser.TenPinBowlingGameLineParser;
import ec.com.java.challenge.bowling.util.Constants;
import ec.com.java.challenge.bowling.util.GameInputType;
import ec.com.java.challenge.bowling.util.GameOutputType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

/**
 * <p>TenPinBowlingGameController class.</p>
 *
 * @author Jublar Garcia Ramos
 * @version 1.0
 */
public class TenPinBowlingGameController implements IBowlingGameController {

    IGameInputReader inputReader;
    IGameOutputWriter outputWriter;

    private final GameInputType gameInputType;
    private final GameOutputType gameOutputType;
    private Map<String, String> extraInputInfo;

    /**
     * <p>Constructor for TenPinBowlingGameController.</p>
     *
     * @param gameInputType  a {@link ec.com.java.challenge.bowling.util.GameInputType} object.
     * @param gameOutputType a {@link ec.com.java.challenge.bowling.util.GameOutputType} object.
     * @param extraInputInfo map to set extra info for use in input, e.g. file name for file input.
     */
    public TenPinBowlingGameController(GameInputType gameInputType, GameOutputType gameOutputType, Map<String, String> extraInputInfo) {
        this.gameInputType = gameInputType;
        this.gameOutputType = gameOutputType;
        this.extraInputInfo = extraInputInfo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        IBowlingGameParser gameParser;
        buildInputReader();
        buildOutputWriter();
        List<String> lines = inputReader.read();
        gameParser = new TenPinBowlingGameLineParser(lines);
        List<IBowlingGame> games = gameParser.createAll();
        outputWriter.write(games);
    }

    private void buildInputReader() {
        if (gameInputType != GameInputType.FILE) {
            System.out.println(String.format("Type of input %s not implemented yet", gameInputType));
            return;
        }
        if (gameInputType != GameInputType.FILE &&
                (extraInputInfo == null ||
                        !extraInputInfo.containsKey(Constants.EXTRA_INFO_FILE_KEY) ||
                        "".equals(extraInputInfo.get(Constants.EXTRA_INFO_FILE_KEY)))) {
            System.out.print("Please enter file path to load game input: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                extraInputInfo.put(Constants.EXTRA_INFO_FILE_KEY, reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            inputReader = new FileGameInputReader(extraInputInfo.get(Constants.EXTRA_INFO_FILE_KEY), new FileTabGameInputValidator());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void buildOutputWriter() {
        if (gameOutputType == GameOutputType.CONSOLE) {
            outputWriter = new ConsoleGameOutputWriter();
        } else {
            System.out.println(String.format("Type of output %s not implemented yet", gameOutputType));
        }
    }

}
