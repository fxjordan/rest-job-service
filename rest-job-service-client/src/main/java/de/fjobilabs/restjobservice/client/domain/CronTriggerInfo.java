package de.fjobilabs.restjobservice.client.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Felix Jordan
 * @since 02.08.2016 - 00:21:55
 * @version 1.0
 */
public class CronTriggerInfo {
    
    private String second;
    private String minute;
    private String hour;
    private String dayOfMonth;
    private String month;
    private String dayOfWeek;
    private String year;
    
    public String getSecond() {
        return second;
    }
    
    public void setSecond(String second) {
        this.second = second;
    }
    
    public String getMinute() {
        return minute;
    }
    
    public void setMinute(String minute) {
        this.minute = minute;
    }
    
    public String getHour() {
        return hour;
    }
    
    public void setHour(String hour) {
        this.hour = hour;
    }
    
    @JsonProperty("day-of-month")
    public String getDayOfMonth() {
        return dayOfMonth;
    }
    
    @JsonProperty("day-of-month")
    public void setDayOfMonth(String dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }
    
    public String getMonth() {
        return month;
    }
    
    public void setMonth(String month) {
        this.month = month;
    }
    
    @JsonProperty("day-of-week")
    public String getDayOfWeek() {
        return dayOfWeek;
    }
    
    @JsonProperty("day-of-week")
    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
    
    public String getYear() {
        return year;
    }
    
    public void setYear(String year) {
        this.year = year;
    }
}
