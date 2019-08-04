package ec.com.java.challenge.bowling.input.validator;

public interface IGameInputValidator<T> {
    boolean validate(T input);
}
