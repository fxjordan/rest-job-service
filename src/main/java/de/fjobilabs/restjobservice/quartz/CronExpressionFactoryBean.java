package de.fjobilabs.restjobservice.quartz;

import java.text.ParseException;

import org.quartz.CronExpression;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author Felix Jordan
 * @since 02.08.2016 - 00:55:56
 * @version 1.0
 */
public class CronExpressionFactoryBean implements FactoryBean<CronExpression>, InitializingBean {
    
    private String second;
    private String minute;
    private String hour;
    private String dayOfMonth;
    private String month;
    private String dayOfWeek;
    private String year;
    
    private CronExpression cronExpression;
    
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
    
    public String getDayOfMonth() {
        return dayOfMonth;
    }
    
    public void setDayOfMonth(String dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }
    
    public String getMonth() {
        return month;
    }
    
    public void setMonth(String month) {
        this.month = month;
    }
    
    public String getDayOfWeek() {
        return dayOfWeek;
    }
    
    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
    
    public String getYear() {
        return year;
    }
    
    public void setYear(String year) {
        this.year = year;
    }
    
    @Override
    public void afterPropertiesSet() throws ParseException {
        StringBuffer buffer = new StringBuffer();
        buffer.append(this.second);
        buffer.append(' ');
        buffer.append(this.minute);
        buffer.append(' ');
        buffer.append(this.hour);
        buffer.append(' ');
        buffer.append(this.dayOfMonth);
        buffer.append(' ');
        buffer.append(this.month);
        buffer.append(' ');
        buffer.append(this.dayOfWeek);
        buffer.append(' ');
        buffer.append(this.year);
        
        this.cronExpression = new CronExpression(buffer.toString());
    }
    
    @Override
    public CronExpression getObject()  {
        return cronExpression;
    }
    
    @Override
    public Class<?> getObjectType() {
        return CronExpression.class;
    }
    
    @Override
    public boolean isSingleton() {
        return true;
    }
}
