package ec.com.java.challenge.bowling.exception;

/**
 * <p>InputValidationException class.</p>
 *
 * @author Jublar Garcia Ramos
 * @version 1.0
 */
public class InputValidationException extends RuntimeException {

    /**
     * <p>Constructor.</p>
     *
     * @param  message the detail message (which is saved for later retrieval
     *         by the {@link #getMessage()} method).
     * @param  cause the cause (which is saved for later retrieval by the
     *         {@link #getCause()} method).  (A <tt>null</tt> value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     */
    public InputValidationException(String message, Throwable cause) {
        super(message, cause);
    }

}
