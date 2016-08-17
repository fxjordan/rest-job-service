package de.fjobilabs.restjobservice.callbackserver.exceptions;

/**
 * @author Felix Jordan
 * @since 16.08.2016 - 21:40:35
 * @version 1.0
 */
public class CallbackInstantiationException extends RuntimeException {
    
    private static final long serialVersionUID = 5918406461735139941L;
    
    public CallbackInstantiationException() {
        super();
    }
    
    public CallbackInstantiationException(String message) {
        super(message);
    }
    
    public CallbackInstantiationException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public CallbackInstantiationException(Throwable cause) {
        super(cause);
    }
}
