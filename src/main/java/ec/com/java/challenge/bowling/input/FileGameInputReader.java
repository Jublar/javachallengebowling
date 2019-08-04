package ec.com.java.challenge.bowling.input;

import ec.com.java.challenge.bowling.input.validator.IGameInputValidator;

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

    public FileGameInputReader(String fileName, IGameInputValidator inputValidator) {
        this.fileName = fileName;
        this.inputValidator = inputValidator;
    }

    @Override
    public List<String> read() {
        if (fileName == null || !Files.exists(Paths.get(fileName))) {
            System.err.println(String.format("File %s not found.", fileName));
            return Collections.emptyList();
        }
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            return stream.filter(inputValidator::validate).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
