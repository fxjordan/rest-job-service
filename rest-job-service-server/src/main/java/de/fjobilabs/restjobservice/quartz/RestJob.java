package de.fjobilabs.restjobservice.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author Felix Jordan
 * @since 16.08.2016 - 01:50:24
 * @version 1.0
 */
public abstract class RestJob implements Job {
    
    private Exception exception;
    
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            Object result = executeInternal(context);
            context.setResult(result);
        } catch (JobExecutionException e) {
            throw e;
        } catch (Exception e) {
            this.exception = e;
        }
    }
    
    protected abstract Object executeInternal(JobExecutionContext context) throws JobExecutionException;
    
    public Exception getException() {
        return this.exception;
    }
}
