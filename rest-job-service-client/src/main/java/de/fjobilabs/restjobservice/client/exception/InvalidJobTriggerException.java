package de.fjobilabs.restjobservice.client.exception;

import de.fjobilabs.springutils.web.resources.exception.RestResourceException;

/**
 * @author Felix Jordan
 * @since 26.08.2016 - 20:44:09
 * @version 1.0
 */
public class InvalidJobTriggerException extends RestResourceException {
    
    public static final int CODE = 4202;
    
    private static final long serialVersionUID = 5918406461735139941L;
    
    public InvalidJobTriggerException() {
        super();
    }
    
    public InvalidJobTriggerException(String message) {
        super(message);
    }
    
    public InvalidJobTriggerException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public InvalidJobTriggerException(Throwable cause) {
        super(cause);
    }

    @Override
    public int getCode() {
        return CODE;
    }
}
