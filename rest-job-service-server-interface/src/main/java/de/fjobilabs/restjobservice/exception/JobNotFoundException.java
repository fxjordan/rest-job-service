package de.fjobilabs.restjobservice.exception;

/**
 * @author Felix Jordan
 * @since 02.08.2016 - 22:19:42
 * @version 1.0
 */
public class JobNotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = 5918406461735139941L;
    
    public JobNotFoundException() {
        super();
    }
    
    public JobNotFoundException(String message) {
        super(message);
    }
    
    public JobNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public JobNotFoundException(Throwable cause) {
        super(cause);
    }
}
