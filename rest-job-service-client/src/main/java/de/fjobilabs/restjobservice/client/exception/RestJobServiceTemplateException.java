package de.fjobilabs.restjobservice.client.exception;

/**
 * @author Felix Jordan
 * @since 14.08.2016 - 01:51:58
 * @version 1.0
 */
public class RestJobServiceTemplateException extends RuntimeException {
    
    private static final long serialVersionUID = 6328676570464617491L;
    
    public RestJobServiceTemplateException() {
        super();
    }
    
    public RestJobServiceTemplateException(String message) {
        super(message);
    }
    
    public RestJobServiceTemplateException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public RestJobServiceTemplateException(Throwable cause) {
        super(cause);
    }
}
