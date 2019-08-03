package ec.com.java.challenge.bowling.input;

import ec.com.java.challenge.bowling.input.validator.IGameInputValidator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileGameInputReader implements IGameInputReader {

    private String fileName;
    private IGameInputValidator inputValidator;

    public FileGameInputReader(String fileName, IGameInputValidator inputValidator){
        this.fileName = fileName;
        this.inputValidator = inputValidator;
    }

    @Override
    public List<String> read() {
        List<String> fileLines = null;
        if(fileName != null) {
            File file = new File(fileName);
            if(file.exists()) {
                try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
                    fileLines = stream.collect(Collectors.toList());
                    fileLines.stream().filter(l -> inputValidator.validate(l));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileLines;
    }
}
