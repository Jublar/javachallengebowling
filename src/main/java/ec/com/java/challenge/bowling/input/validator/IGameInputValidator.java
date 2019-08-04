package ec.com.java.challenge.bowling.input.validator;

/**
 * <p>IGameInputValidator interface.</p>
 *
 * @author Jublar Garcia Ramos
 * @version 1.0
 */
public interface IGameInputValidator<T> {
    /**
     * <p>validate.</p>
     *
     * @param input a T object.
     * @return a boolean.
     */
    boolean validate(T input);
}
