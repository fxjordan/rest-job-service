package de.fjobilabs.restjobservice.exception;

/**
 * @author Felix Jordan
 * @since 15.08.2016 - 22:26:52
 * @version 1.0
 */
public class UnknownJobActionException extends RuntimeException {
    
    private static final long serialVersionUID = 5918406461735139941L;
    
    public UnknownJobActionException() {
        super();
    }
    
    public UnknownJobActionException(String message) {
        super(message);
    }
    
    public UnknownJobActionException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public UnknownJobActionException(Throwable cause) {
        super(cause);
    }
}
