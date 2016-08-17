package de.fjobilabs.restjobservice.callbackserver.exceptions;

/**
 * @author Felix Jordan
 * @since 17.08.2016 - 18:51:14
 * @version 1.0
 */
public class CallbackExecutionException extends RuntimeException {
    
    private static final long serialVersionUID = 5918406461735139941L;
    
    public CallbackExecutionException() {
        super();
    }
    
    public CallbackExecutionException(String message) {
        super(message);
    }
    
    public CallbackExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public CallbackExecutionException(Throwable cause) {
        super(cause);
    }
}
