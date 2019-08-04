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

public class TenPinBowlingGameControler implements IBowlingGameControler {

    private IGameInputReader inputReader;
    private IBowlingGameParser gameParser;
    private IGameOutputWriter outputWriter;
    private GameInputType gameInputType;
    private GameOutputType gameOutputType;

    public TenPinBowlingGameControler(GameInputType gameInputType, GameOutputType gameOutputType) {
        this.gameInputType = gameInputType;
        this.gameOutputType = gameOutputType;
    }

    public void run() {
        buildInputReader();
        buildOutputWriter();
        List<String> lines = inputReader.read();
        gameParser = new TenPinBowlingGameLineParser(lines);
        List<IBowlingGame> games = gameParser.createAll();
        outputWriter.write(games);
    }

    private void buildInputReader() {
        switch (gameInputType) {
            case FILE:
                System.out.print("Please enter file path to load game input: ");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String fileName = "";
                try {
                    fileName = reader.readLine();
                    inputReader = new FileGameInputReader(fileName, new FileTabGameInputValidator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println(String.format("Type of input %s not implemented yet", gameInputType));
                break;
        }
    }

    private void buildOutputWriter() {
        switch (gameOutputType) {
            case CONSOLE:
                outputWriter = new ConsoleGameOutputWriter();
                break;
            default:
                System.out.println(String.format("Type of output %s not implemented yet", gameOutputType));
                break;
        }
    }
}
