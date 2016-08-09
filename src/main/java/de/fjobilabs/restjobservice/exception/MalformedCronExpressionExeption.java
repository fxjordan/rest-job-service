package de.fjobilabs.restjobservice.exception;

/**
 * @author Felix Jordan
 * @since 02.08.2016 - 01:14:35
 * @version 1.0
 */
public class MalformedCronExpressionExeption extends RuntimeException {
    
    private static final long serialVersionUID = 8287311847817155840L;
    
    public MalformedCronExpressionExeption() {
        super();
    }
    
    public MalformedCronExpressionExeption(String message) {
        super(message);
    }
    
    public MalformedCronExpressionExeption(String message, Throwable cause) {
        super(message, cause);
    }
    
    public MalformedCronExpressionExeption(Throwable cause) {
        super(cause);
    }
}
