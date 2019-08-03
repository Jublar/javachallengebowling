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

    @Test
    public void validateLineInvalid() {
        Assert.assertFalse(fileValidator.validate(""));
    }

    @Test
    public void validateLineLetter() {
        Assert.assertFalse(fileValidator.validate("PlayerName   asd"));
    }

    @Test
    public void validateLineRangeNegative() {
        Assert.assertFalse(fileValidator.validate("PlayerName   -1"));
    }

    @Test
    public void validateLineRangeMoreThanTen() {
        Assert.assertFalse(fileValidator.validate("PlayerName   11"));
    }
}
