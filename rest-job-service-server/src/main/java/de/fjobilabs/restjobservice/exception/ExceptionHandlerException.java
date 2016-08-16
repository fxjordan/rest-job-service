package de.fjobilabs.restjobservice.exception;

/**
 * @author Felix Jordan
 * @since 16.08.2016 - 15:43:16
 * @version 1.0
 */
public class ExceptionHandlerException extends RuntimeException {
    
    private static final long serialVersionUID = -883964229859794322L;
    
    public ExceptionHandlerException() {
        super();
    }
    
    public ExceptionHandlerException(String message) {
        super(message);
    }
    
    public ExceptionHandlerException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ExceptionHandlerException(Throwable cause) {
        super(cause);
    }
}
