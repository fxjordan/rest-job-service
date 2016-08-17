package de.fjobilabs.restjobservice.callbackserver.domain;

/**
 * @author Felix Jordan
 * @since 16.08.2016 - 21:11:13
 * @version 1.0
 */
public class JobCallbackData {
    
    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";
    public static final String ERROR = "error";
    
    private String jobName;
    private String status;
    private Object result;
    
    public String getJobName() {
        return jobName;
    }
    
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Object getResult() {
        return result;
    }
    
    public void setResult(Object result) {
        this.result = result;
    }
}
