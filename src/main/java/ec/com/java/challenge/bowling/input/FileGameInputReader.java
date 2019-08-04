package ec.com.java.challenge.bowling.input;

import ec.com.java.challenge.bowling.exception.InputValidationException;
import ec.com.java.challenge.bowling.input.validator.IGameInputValidator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class to read bowling game lines from file.
 *
 * @author Jublar Garcia Ramos
 * @version 1.0
 */
public class FileGameInputReader implements IGameInputReader {

    private final String fileName;
    private final IGameInputValidator inputValidator;

    /**
     * Constructor
     *
     * @param fileName File name to read input.
     * @param inputValidator Instance of {@link ec.com.java.challenge.bowling.input.validator.IGameInputValidator} to validate input.
     * @throws java.io.IOException Exception thrown when input file does not exists.
     * @throws java.lang.IllegalArgumentException Exception thrown when fileName parameter is null or empty.
     */
    public FileGameInputReader(String fileName, IGameInputValidator inputValidator) throws IOException {
        if (fileName == null || "".equals(fileName)) {
            throw new IllegalArgumentException("Argument fileName is required.");
        }
        if(!Files.exists(Paths.get(fileName))) {
            throw new FileNotFoundException(String.format("File %s not found.", fileName));
        }
        this.fileName = fileName;
        this.inputValidator = inputValidator;
    }

    /** {@inheritDoc} */
    @Override
    public List<String> read() {
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            return stream.filter(inputValidator::validate).collect(Collectors.toList());
        } catch (IOException e) {
            throw new InputValidationException("Error while reading game input", e);
        }
    }
}
