package de.fjobilabs.restjobservice.callbackserver.exceptions;

/**
 * @author Felix Jordan
 * @since 16.08.2016 - 21:25:17
 * @version 1.0
 */
public class CallbackNotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = 5918406461735139941L;
    
    public CallbackNotFoundException() {
        super();
    }
    
    public CallbackNotFoundException(String message) {
        super(message);
    }
    
    public CallbackNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public CallbackNotFoundException(Throwable cause) {
        super(cause);
    }
}
