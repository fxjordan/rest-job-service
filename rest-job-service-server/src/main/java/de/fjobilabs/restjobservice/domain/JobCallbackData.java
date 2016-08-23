package de.fjobilabs.restjobservice.domain;

/**
 * @author Felix Jordan
 * @since 13.08.2016 - 00:16:44
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
    
    @Override
    public String toString() {
        return String.format("JobCallbackData(jobName=%s; status=%s; result=%s)", jobName, status,
                result);
    }
}
