package de.fjobilabs.restjobservice.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Felix Jordan
 * @since 24.06.2016 - 23:40:13
 * @version 1.0
 */
public class RestResource {
    
    public static final String SUCESS = "sucess";
    public static final String FAIL = "fail";
    public static final String ERROR = "error";
    
    private String requestId;
    private String status;
    private Object data;
    
    @JsonProperty("request-id")
    public String getRequestId() {
        return requestId;
    }
    
    @JsonProperty("request-id")
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Object getData() {
        return data;
    }
    
    public void setData(Object data) {
        this.data = data;
    }
    
}
