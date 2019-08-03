package ec.com.java.challenge.bowling.input.validator;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class FileTabGameInputValidatorTest {
    private static IGameInputValidator fileValidator;

    @BeforeClass
    public static void init() {
        fileValidator = new FileTabGameInputValidator();
    }

    @Test
    public void validateLineOk() {
        Assert.assertTrue(fileValidator.validate("PlayerName    0"));
    }

}
