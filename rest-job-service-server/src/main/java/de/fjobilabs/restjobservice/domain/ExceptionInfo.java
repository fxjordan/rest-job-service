package de.fjobilabs.restjobservice.domain;

/**
 * @author Felix Jordan
 * @since 16.08.2016 - 14:04:05
 * @version 1.0
 */
public class ExceptionInfo {
    
    private String exception;
    private String message;
    
    public String getException() {
        return exception;
    }
    
    public void setException(String exception) {
        this.exception = exception;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}
