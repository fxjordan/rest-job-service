package de.fjobilabs.restjobservice.client.domain;

import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Felix Jordan
 * @since 01.08.2016 - 22:22:19
 * @version 1.0
 */
public class JobInfo {
    
    private String name;
    private String action;
    private SimpleTriggerInfo simpleTrigger;
    private CronTriggerInfo cronTrigger;
    private Map<String, Object> data;
    private String callback;
    private Date lastExecutionTime;
    private Date nextExecutionTime;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAction() {
        return action;
    }
    
    public void setAction(String action) {
        this.action = action;
    }
    
    @JsonProperty("simple-trigger")
    @JsonInclude(Include.NON_NULL)
    public SimpleTriggerInfo getSimpleTrigger() {
        return simpleTrigger;
    }
    
    @JsonProperty("simple-trigger")
    public void setSimpleTrigger(SimpleTriggerInfo simpleTrigger) {
        this.simpleTrigger = simpleTrigger;
    }
    
    @JsonProperty("cron-trigger")
    @JsonInclude(Include.NON_NULL)
    public CronTriggerInfo getCronTrigger() {
        return cronTrigger;
    }
    
    @JsonProperty("cron-trigger")
    public void setCronTrigger(CronTriggerInfo cronTrigger) {
        this.cronTrigger = cronTrigger;
    }
    
    @JsonInclude(Include.NON_NULL)
    public Map<String, Object> getData() {
        return data;
    }
    
    public void setData(Map<String, Object> data) {
        this.data = data;
    }
    
    public String getCallback() {
        return callback;
    }
    
    public void setCallback(String callback) {
        this.callback = callback;
    }
    
    @JsonProperty("last-execution-time")
    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    public Date getLastExecutionTime() {
        return lastExecutionTime;
    }
    
    @JsonProperty("last-execution-time")
    public void setLastExecutionTime(Date lastExecutionTime) {
        this.lastExecutionTime = lastExecutionTime;
    }
    
    @JsonProperty("next-execution-time")
    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    public Date getNextExecutionTime() {
        return nextExecutionTime;
    }
    
    @JsonProperty("next-execution-time")
    public void setNextExecutionTime(Date nextExecutionTime) {
        this.nextExecutionTime = nextExecutionTime;
    }
}
