package ec.com.java.challenge.bowling.controller;

import ec.com.java.challenge.bowling.game.IBowlingGame;
import ec.com.java.challenge.bowling.input.FileGameInputReader;
import ec.com.java.challenge.bowling.input.IGameInputReader;
import ec.com.java.challenge.bowling.input.validator.FileTabGameInputValidator;
import ec.com.java.challenge.bowling.output.ConsoleGameOutputWriter;
import ec.com.java.challenge.bowling.output.IGameOutputWriter;
import ec.com.java.challenge.bowling.parser.IBowlingGameParser;
import ec.com.java.challenge.bowling.parser.TenPinBowlingGameLineParser;
import ec.com.java.challenge.bowling.util.GameInputType;
import ec.com.java.challenge.bowling.util.GameOutputType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

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
    private String fileName;
    /**
     * <p>Constructor for TenPinBowlingGameController.</p>
     *
     * @param gameInputType a {@link ec.com.java.challenge.bowling.util.GameInputType} object.
     * @param gameOutputType a {@link ec.com.java.challenge.bowling.util.GameOutputType} object.
     */
    public TenPinBowlingGameController(GameInputType gameInputType, GameOutputType gameOutputType, String fileName) {
        this.gameInputType = gameInputType;
        this.gameOutputType = gameOutputType;
        this.fileName = fileName;
    }

    /** {@inheritDoc} */
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
        if(fileName == null || "".equals(fileName)) {
            System.out.print("Please enter file path to load game input: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                fileName = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            inputReader = new FileGameInputReader(fileName, new FileTabGameInputValidator());
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
