package de.fjobilabs.restjobservice.client.util;

import java.util.Map;

import de.fjobilabs.restjobservice.client.exception.InternalSchedulerException;
import de.fjobilabs.restjobservice.client.exception.InvalidJobTriggerException;
import de.fjobilabs.restjobservice.client.exception.JobNotFoundException;
import de.fjobilabs.restjobservice.client.exception.MalformedCronExpressionExeption;
import de.fjobilabs.restjobservice.client.exception.UnknownJobActionException;
import de.fjobilabs.springutils.web.resources.exception.DefaultRestResourceExceptionFactory;
import de.fjobilabs.springutils.web.resources.exception.MapExceptionCodeContext;
import de.fjobilabs.springutils.web.resources.exception.RestResourceException;

/**
 * @author Felix Jordan
 * @since 26.08.2016 - 22:05:24
 * @version 1.0
 */
public class RestJobServiceExceptionFactory extends DefaultRestResourceExceptionFactory {
    
    private static RestJobServiceExceptionFactory instance;
    
    private RestJobServiceExceptionFactory() {
    }
    
    private void registerExceptions() {
        MapExceptionCodeContext context = new MapExceptionCodeContext();
        Map<Integer, Class<? extends RestResourceException>> classes = context.getExceptions();
        classes.put(InternalSchedulerException.CODE, InternalSchedulerException.class);
        classes.put(InvalidJobTriggerException.CODE, InvalidJobTriggerException.class);
        classes.put(JobNotFoundException.CODE, JobNotFoundException.class);
        classes.put(MalformedCronExpressionExeption.CODE, MalformedCronExpressionExeption.class);
        classes.put(UnknownJobActionException.CODE, UnknownJobActionException.class);
        setContext(context);
    }
    
    public static RestJobServiceExceptionFactory getInstance() {
        if (instance == null) {
            instance = new RestJobServiceExceptionFactory();
            instance.registerExceptions();
        }
        return instance;
    }
}
