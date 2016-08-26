package de.fjobilabs.restjobservice.client.exception;

import de.fjobilabs.springutils.web.resources.exception.RestResourceException;

/**
 * @author Felix Jordan
 * @since 26.08.2016 - 20:44:09
 * @version 1.0
 */
public class MalformedCronExpressionExeption extends RestResourceException {
    
    public static final int CODE = 4204;
    
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

    @Override
    public int getCode() {
        return CODE;
    }
}
