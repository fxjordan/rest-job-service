package de.fjobilabs.restjobservice.exception;

/**
 * @author Felix Jordan
 * @since 02.08.2016 - 01:14:35
 * @version 1.0
 */
public class InternalSchedulerException extends RuntimeException {
    
    private static final long serialVersionUID = -883964229859794322L;
    
    public InternalSchedulerException() {
        super();
    }
    
    public InternalSchedulerException(String message) {
        super(message);
    }
    
    public InternalSchedulerException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public InternalSchedulerException(Throwable cause) {
        super(cause);
    }
}
