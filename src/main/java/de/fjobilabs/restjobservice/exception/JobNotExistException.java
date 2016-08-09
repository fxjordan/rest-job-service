package de.fjobilabs.restjobservice.exception;

/**
 * @author Felix Jordan
 * @since 02.08.2016 - 22:19:42
 * @version 1.0
 */
public class JobNotExistException extends RuntimeException {
    
    private static final long serialVersionUID = 5918406461735139941L;
    
    public JobNotExistException() {
        super();
    }
    
    public JobNotExistException(String message) {
        super(message);
    }
    
    public JobNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public JobNotExistException(Throwable cause) {
        super(cause);
    }
}
