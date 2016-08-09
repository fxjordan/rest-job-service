package de.fjobilabs.restjobservice.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Felix Jordan
 * @since 01.08.2016 - 23:35:39
 * @version 1.0
 */
public class SimpleTriggerInfo {
    
    private int repeatCount = -1;
    private long repeatIntervall;
    private Date startTime;
    private Date endTime;
    
    @JsonProperty("repeat-count")
    public int getRepeatCount() {
        return repeatCount;
    }
    
    @JsonProperty("repeat-count")
    public void setRepeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
    }
    
    @JsonProperty("repeat-intervall")
    public long getRepeatIntervall() {
        return repeatIntervall;
    }
    
    @JsonProperty("repeat-intervall")
    public void setRepeatIntervall(long repeatIntervall) {
        this.repeatIntervall = repeatIntervall;
    }
    
    @JsonProperty("start-time")
    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    public Date getStartTime() {
        return startTime;
    }
    
    @JsonProperty("start-time")
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    
    @JsonProperty("end-time")
    @JsonInclude(Include.NON_NULL)
    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    public Date getEndTime() {
        return endTime;
    }
    
    @JsonProperty("end-time")
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
