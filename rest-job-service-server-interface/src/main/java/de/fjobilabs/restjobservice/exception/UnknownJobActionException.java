package de.fjobilabs.restjobservice.exception;

import de.fjobilabs.springutils.web.resources.exception.RestResourceException;

/**
 * @author Felix Jordan
 * @since 15.08.2016 - 22:26:52
 * @version 1.0
 */
public class UnknownJobActionException extends RestResourceException {
    
    public static final int CODE = 4205;
    
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

    @Override
    public int getCode() {
        return CODE;
    }
}
