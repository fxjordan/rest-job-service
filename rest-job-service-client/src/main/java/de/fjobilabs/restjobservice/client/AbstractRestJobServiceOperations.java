package de.fjobilabs.restjobservice.client;

import java.net.URL;
import java.util.List;
import java.util.Map;

import org.springframework.util.AlternativeJdkIdGenerator;
import org.springframework.util.IdGenerator;

import de.fjobilabs.restjobservice.client.domain.CronTriggerInfo;
import de.fjobilabs.restjobservice.client.domain.JobInfo;
import de.fjobilabs.restjobservice.client.domain.SimpleTriggerInfo;

/**
 * @author Felix Jordan
 * @since 14.08.2016 - 00:50:45
 * @version 1.0
 */
public abstract class AbstractRestJobServiceOperations implements RestJobServiceOperations {
    
    private IdGenerator idGenerator = new AlternativeJdkIdGenerator();
    
    @Override
    public JobInfo createJob(String action) {
        return createJob(action, (Map<String, Object>) null, (URL) null);
    }
    
    @Override
    public JobInfo createJob(String action, Map<String, Object> data) {
        return createJob(action, data, (URL) null);
    }
    
    @Override
    public JobInfo createJob(String action, SimpleTriggerInfo trigger) {
        return createJob(action, (Map<String, Object>) null, trigger, null);
    }
    
    @Override
    public JobInfo createJob(String action, Map<String, Object> data, SimpleTriggerInfo trigger) {
        return createJob(action, data, trigger, null);
    }
    
    @Override
    public JobInfo createJob(String action, CronTriggerInfo trigger) {
        return createJob(action, (Map<String, Object>) null, trigger, null);
    }
    
    @Override
    public JobInfo createJob(String action, Map<String, Object> data, CronTriggerInfo trigger) {
        return createJob(action, data, trigger, null);
    }
    
    @Override
    public JobInfo createJob(String action, URL callback) {
        return createJob(action, (Map<String, Object>) null, callback);
    }
    
    @Override
    public JobInfo createJob(String action, Map<String, Object> data, URL callback) {
        SimpleTriggerInfo trigger = new SimpleTriggerInfo();
        trigger.setRepeatCount(0);
        return createJob(action, data, trigger, callback);
    }
    
    @Override
    public JobInfo createJob(String action, SimpleTriggerInfo trigger, URL callback) {
        return createJob(action, (Map<String, Object>) null, trigger, callback);
    }
    
    @Override
    public JobInfo createJob(String action, Map<String, Object> data, SimpleTriggerInfo trigger,
            URL callback) {
        return createJob(this.idGenerator.generateId().toString(), action, data, trigger, callback);
    }
    
    @Override
    public JobInfo createJob(String action, CronTriggerInfo trigger, URL callback) {
        return createJob(action, (Map<String, Object>) null, trigger, callback);
    }
    
    @Override
    public JobInfo createJob(String action, Map<String, Object> data, CronTriggerInfo trigger,
            URL callback) {
        return createJob(this.idGenerator.generateId().toString(), action, data, trigger, callback);
    }
    
    @Override
    public JobInfo createJob(String id, String action) {
        return createJob(id, action, (Map<String, Object>) null, (URL) null);
    }
    
    @Override
    public JobInfo createJob(String id, String action, Map<String, Object> data) {
        return createJob(id, action, data, (URL) null);
    }
    
    @Override
    public JobInfo createJob(String id, String action, SimpleTriggerInfo trigger) {
        return createJob(id, action, null, trigger, null);
    }
    
    @Override
    public JobInfo createJob(String id, String action, Map<String, Object> data,
            SimpleTriggerInfo trigger) {
        return createJob(id, action, data, trigger, null);
    }
    
    @Override
    public JobInfo createJob(String id, String action, CronTriggerInfo trigger) {
        return createJob(id, action, null, trigger, null);
    }
    
    @Override
    public JobInfo createJob(String id, String action, Map<String, Object> data,
            CronTriggerInfo trigger) {
        return createJob(id, action, data, trigger, null);
    }
    
    @Override
    public JobInfo createJob(String id, String action, URL callback) {
        return createJob(id, action, (Map<String, Object>) null, callback);
    }
    
    @Override
    public JobInfo createJob(String id, String action, Map<String, Object> data, URL callback) {
        SimpleTriggerInfo trigger = new SimpleTriggerInfo();
        trigger.setRepeatCount(0);
        return createJob(id, action, data, trigger, callback);
    }
    
    @Override
    public JobInfo createJob(String id, String action, SimpleTriggerInfo trigger, URL callback) {
        return createJob(id, action, null, trigger, callback);
    }
    
    @Override
    public JobInfo createJob(String id, String action, Map<String, Object> data,
            SimpleTriggerInfo trigger, URL callback) {
        JobInfo jobInfo = new JobInfo();
        jobInfo.setName(id);
        jobInfo.setAction(action);
        jobInfo.setData(data);
        jobInfo.setSimpleTrigger(trigger);
        jobInfo.setCallback(callback.toString());
        return createJob(jobInfo);
    }
    
    @Override
    public JobInfo createJob(String id, String action, CronTriggerInfo trigger, URL callback) {
        return createJob(id, action, null, trigger, callback);
    }
    
    @Override
    public JobInfo createJob(String id, String action, Map<String, Object> data,
            CronTriggerInfo trigger, URL callback) {
        JobInfo jobInfo = new JobInfo();
        jobInfo.setName(id);
        jobInfo.setAction(action);
        jobInfo.setData(data);
        jobInfo.setCronTrigger(trigger);
        jobInfo.setCallback(callback.toString());
        return createJob(jobInfo);
    }
    
    @Override
    public JobInfo updateJob(String id, String action) {
        return updateJob(id, action, (Map<String, Object>) null, (URL) null);
    }
    
    @Override
    public JobInfo updateJob(String id, String action, Map<String, Object> data) {
        return updateJob(id, action, data, (URL) null);
    }
    
    @Override
    public JobInfo updateJob(String id, String action, SimpleTriggerInfo trigger) {
        return updateJob(id, action, null, trigger, null);
    }
    
    @Override
    public JobInfo updateJob(String id, String action, Map<String, Object> data,
            SimpleTriggerInfo trigger) {
        return updateJob(id, action, data, trigger, null);
    }
    
    @Override
    public JobInfo updateJob(String id, String action, CronTriggerInfo trigger) {
        return updateJob(id, action, null, trigger, null);
    }
    
    @Override
    public JobInfo updateJob(String id, String action, Map<String, Object> data,
            CronTriggerInfo trigger) {
        return updateJob(id, action, data, trigger, null);
    }
    
    @Override
    public JobInfo updateJob(String id, String action, URL callback) {
        return updateJob(id, action, (Map<String, Object>) null, callback);
    }
    
    @Override
    public JobInfo updateJob(String id, String action, Map<String, Object> data, URL callback) {
        SimpleTriggerInfo trigger = new SimpleTriggerInfo();
        trigger.setRepeatCount(0);
        return updateJob(id, action, data, trigger, callback);
    }
    
    @Override
    public JobInfo updateJob(String id, String action, SimpleTriggerInfo trigger, URL callback) {
        return updateJob(id, action, null, trigger, callback);
    }
    
    @Override
    public JobInfo updateJob(String id, String action, Map<String, Object> data,
            SimpleTriggerInfo trigger, URL callback) {
        JobInfo jobInfo = new JobInfo();
        jobInfo.setName(id);
        jobInfo.setAction(action);
        jobInfo.setData(data);
        jobInfo.setSimpleTrigger(trigger);
        return updateJob(jobInfo);
    }
    
    @Override
    public JobInfo updateJob(String id, String action, CronTriggerInfo trigger, URL callback) {
        return updateJob(id, action, null, trigger, callback);
    }
    
    @Override
    public JobInfo updateJob(String id, String action, Map<String, Object> data,
            CronTriggerInfo trigger, URL callback) {
        JobInfo jobInfo = new JobInfo();
        jobInfo.setName(id);
        jobInfo.setAction(action);
        jobInfo.setData(data);
        jobInfo.setCronTrigger(trigger);
        return updateJob(jobInfo);
    }
    
    @Override
    public abstract List<String> getJobNames();
    
    @Override
    public abstract JobInfo getJobInfo(String jobName);
    
    @Override
    public abstract JobInfo updateJob(JobInfo jobInfo);
    
    @Override
    public abstract void deleteJob(String jobName);
    
    @Override
    public abstract JobInfo createJob(JobInfo jobInfo);
    
    public IdGenerator getIdGenerator() {
        return this.idGenerator;
    }
    
    public void setIdGenerator(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }
}
