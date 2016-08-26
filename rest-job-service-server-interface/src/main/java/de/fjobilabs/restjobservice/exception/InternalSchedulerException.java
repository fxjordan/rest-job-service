package de.fjobilabs.restjobservice.exception;

import de.fjobilabs.springutils.web.resources.exception.RestResourceException;

/**
 * @author Felix Jordan
 * @since 02.08.2016 - 01:14:35
 * @version 1.0
 */
public class InternalSchedulerException extends RestResourceException {
    
    public static final int CODE = 4201;
    
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

    @Override
    public int getCode() {
        return CODE;
    }
}
