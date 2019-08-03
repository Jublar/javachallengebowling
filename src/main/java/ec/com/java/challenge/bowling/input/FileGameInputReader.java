package ec.com.java.challenge.bowling.input;

import ec.com.java.challenge.bowling.input.validator.IGameInputValidator;

import java.util.List;

public class FileGameInputReader implements IGameInputReader {

    private String fileName;
    private IGameInputValidator inputValidator;

    public FileGameInputReader(String fileName, IGameInputValidator inputValidator){
        this.fileName = fileName;
        this.inputValidator = inputValidator;
    }

    @Override
    public List<String> read() {
        return null;
    }
}
