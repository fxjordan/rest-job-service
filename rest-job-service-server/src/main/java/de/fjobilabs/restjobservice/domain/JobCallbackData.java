package de.fjobilabs.restjobservice.domain;

/**
 * @author Felix Jordan
 * @since 13.08.2016 - 00:16:44
 * @version 1.0
 */
public class JobCallbackData<T> {
    
    private String jobName;
    private T result;
    private Exception exception;
    
    public String getJobName() {
        return jobName;
    }
    
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
    
    public T getResult() {
        return result;
    }
    
    public void setResult(T result) {
        this.result = result;
    }
    
    public Exception getException() {
        return exception;
    }
    
    public void setException(Exception exception) {
        this.exception = exception;
    }
    
}
