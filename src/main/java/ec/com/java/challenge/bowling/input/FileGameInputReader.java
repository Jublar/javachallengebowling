package ec.com.java.challenge.bowling.input;

import ec.com.java.challenge.bowling.exception.InputValidationException;
import ec.com.java.challenge.bowling.input.validator.IGameInputValidator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileGameInputReader implements IGameInputReader {

    private String fileName;
    private IGameInputValidator inputValidator;

    public FileGameInputReader(String fileName, IGameInputValidator inputValidator) throws IOException {
        if (fileName == null || fileName == "") {
            throw new IllegalArgumentException("Argument fileName is required.");
        }
        if(!Files.exists(Paths.get(fileName))) {
            throw new FileNotFoundException(String.format("File %s not found.", fileName));
        }
        this.fileName = fileName;
        this.inputValidator = inputValidator;
    }

    @Override
    public List<String> read() {
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            return stream.filter(inputValidator::validate).collect(Collectors.toList());
        } catch (IOException e) {
            throw new InputValidationException("Error while reading game input", e);
        }
    }
}
