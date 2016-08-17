package de.fjobilabs.restjobservice.client.exception;

/**
 * @author Felix Jordan
 * @since 17.08.2016 - 23:49:21
 * @version 1.0
 */
public class CallbackRestJobServiceTemplateException extends RuntimeException {
    
    private static final long serialVersionUID = 6328676570464617491L;
    
    public CallbackRestJobServiceTemplateException() {
        super();
    }
    
    public CallbackRestJobServiceTemplateException(String message) {
        super(message);
    }
    
    public CallbackRestJobServiceTemplateException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public CallbackRestJobServiceTemplateException(Throwable cause) {
        super(cause);
    }
}
