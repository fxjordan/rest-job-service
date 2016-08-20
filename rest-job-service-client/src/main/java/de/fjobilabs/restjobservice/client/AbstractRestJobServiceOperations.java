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
    public JobInfo createJob(String uri, String action) {
        return createJob(uri, action, (Map<String, Object>) null, (URL) null);
    }
    
    @Override
    public JobInfo createJob(String uri, String action, Map<String, Object> data) {
        return createJob(uri, action, data, (URL) null);
    }
    
    @Override
    public JobInfo createJob(String uri, String action, SimpleTriggerInfo trigger) {
        return createJob(uri, action, (Map<String, Object>) null, trigger, null);
    }
    
    @Override
    public JobInfo createJob(String uri, String action, Map<String, Object> data, SimpleTriggerInfo trigger) {
        return createJob(uri, action, data, trigger, null);
    }
    
    @Override
    public JobInfo createJob(String uri, String action, CronTriggerInfo trigger) {
        return createJob(uri, action, (Map<String, Object>) null, trigger, null);
    }
    
    @Override
    public JobInfo createJob(String uri, String action, Map<String, Object> data, CronTriggerInfo trigger) {
        return createJob(uri, action, data, trigger, null);
    }
    
    @Override
    public JobInfo createJob(String uri, String action, URL callback) {
        return createJob(uri, action, (Map<String, Object>) null, callback);
    }
    
    @Override
    public JobInfo createJob(String uri, String action, Map<String, Object> data, URL callback) {
        SimpleTriggerInfo trigger = new SimpleTriggerInfo();
        trigger.setRepeatCount(0);
        return createJob(uri, action, data, trigger, callback);
    }
    
    @Override
    public JobInfo createJob(String uri, String action, SimpleTriggerInfo trigger, URL callback) {
        return createJob(uri, action, (Map<String, Object>) null, trigger, callback);
    }
    
    @Override
    public JobInfo createJob(String uri, String action, Map<String, Object> data, SimpleTriggerInfo trigger,
            URL callback) {
        return createJob(this.idGenerator.generateId().toString(), action, data, trigger, callback);
    }
    
    @Override
    public JobInfo createJob(String uri, String action, CronTriggerInfo trigger, URL callback) {
        return createJob(uri, action, (Map<String, Object>) null, trigger, callback);
    }
    
    @Override
    public JobInfo createJob(String uri, String action, Map<String, Object> data, CronTriggerInfo trigger,
            URL callback) {
        return createJob(this.idGenerator.generateId().toString(), action, data, trigger, callback);
    }
    
    @Override
    public JobInfo createJob(String uri, String id, String action) {
        return createJob(uri, id, action, (Map<String, Object>) null, (URL) null);
    }
    
    @Override
    public JobInfo createJob(String uri, String id, String action, Map<String, Object> data) {
        return createJob(uri, id, action, data, (URL) null);
    }
    
    @Override
    public JobInfo createJob(String uri, String id, String action, SimpleTriggerInfo trigger) {
        return createJob(uri, id, action, null, trigger, null);
    }
    
    @Override
    public JobInfo createJob(String uri, String id, String action, Map<String, Object> data,
            SimpleTriggerInfo trigger) {
        return createJob(uri, id, action, data, trigger, null);
    }
    
    @Override
    public JobInfo createJob(String uri, String id, String action, CronTriggerInfo trigger) {
        return createJob(uri, id, action, null, trigger, null);
    }
    
    @Override
    public JobInfo createJob(String uri, String id, String action, Map<String, Object> data,
            CronTriggerInfo trigger) {
        return createJob(uri, id, action, data, trigger, null);
    }
    
    @Override
    public JobInfo createJob(String uri, String id, String action, URL callback) {
        return createJob(uri, id, action, (Map<String, Object>) null, callback);
    }
    
    @Override
    public JobInfo createJob(String uri, String id, String action, Map<String, Object> data, URL callback) {
        SimpleTriggerInfo trigger = new SimpleTriggerInfo();
        trigger.setRepeatCount(0);
        return createJob(uri, id, action, data, trigger, callback);
    }
    
    @Override
    public JobInfo createJob(String uri, String id, String action, SimpleTriggerInfo trigger, URL callback) {
        return createJob(uri, id, action, null, trigger, callback);
    }
    
    @Override
    public JobInfo createJob(String uri, String id, String action, Map<String, Object> data,
            SimpleTriggerInfo trigger, URL callback) {
        JobInfo jobInfo = new JobInfo();
        jobInfo.setName(id);
        jobInfo.setAction(action);
        jobInfo.setData(data);
        jobInfo.setSimpleTrigger(trigger);
        jobInfo.setCallback(callback.toString());
        return createJob(uri, jobInfo);
    }
    
    @Override
    public JobInfo createJob(String uri, String id, String action, CronTriggerInfo trigger, URL callback) {
        return createJob(uri, id, action, null, trigger, callback);
    }
    
    @Override
    public JobInfo createJob(String uri, String id, String action, Map<String, Object> data,
            CronTriggerInfo trigger, URL callback) {
        JobInfo jobInfo = new JobInfo();
        jobInfo.setName(id);
        jobInfo.setAction(action);
        jobInfo.setData(data);
        jobInfo.setCronTrigger(trigger);
        jobInfo.setCallback(callback.toString());
        return createJob(uri, jobInfo);
    }
    
    @Override
    public JobInfo updateJob(String uri, String id, String action) {
        return updateJob(uri, id, action, (Map<String, Object>) null, (URL) null);
    }
    
    @Override
    public JobInfo updateJob(String uri, String id, String action, Map<String, Object> data) {
        return updateJob(uri, id, action, data, (URL) null);
    }
    
    @Override
    public JobInfo updateJob(String uri, String id, String action, SimpleTriggerInfo trigger) {
        return updateJob(uri, id, action, null, trigger, null);
    }
    
    @Override
    public JobInfo updateJob(String uri, String id, String action, Map<String, Object> data,
            SimpleTriggerInfo trigger) {
        return updateJob(uri, id, action, data, trigger, null);
    }
    
    @Override
    public JobInfo updateJob(String uri, String id, String action, CronTriggerInfo trigger) {
        return updateJob(uri, id, action, null, trigger, null);
    }
    
    @Override
    public JobInfo updateJob(String uri, String id, String action, Map<String, Object> data,
            CronTriggerInfo trigger) {
        return updateJob(uri, id, action, data, trigger, null);
    }
    
    @Override
    public JobInfo updateJob(String uri, String id, String action, URL callback) {
        return updateJob(uri, id, action, (Map<String, Object>) null, callback);
    }
    
    @Override
    public JobInfo updateJob(String uri, String id, String action, Map<String, Object> data, URL callback) {
        SimpleTriggerInfo trigger = new SimpleTriggerInfo();
        trigger.setRepeatCount(0);
        return updateJob(uri, id, action, data, trigger, callback);
    }
    
    @Override
    public JobInfo updateJob(String uri, String id, String action, SimpleTriggerInfo trigger, URL callback) {
        return updateJob(uri, id, action, null, trigger, callback);
    }
    
    @Override
    public JobInfo updateJob(String uri, String id, String action, Map<String, Object> data,
            SimpleTriggerInfo trigger, URL callback) {
        JobInfo jobInfo = new JobInfo();
        jobInfo.setName(id);
        jobInfo.setAction(action);
        jobInfo.setData(data);
        jobInfo.setSimpleTrigger(trigger);
        return updateJob(uri, jobInfo);
    }
    
    @Override
    public JobInfo updateJob(String uri, String id, String action, CronTriggerInfo trigger, URL callback) {
        return updateJob(uri, id, action, null, trigger, callback);
    }
    
    @Override
    public JobInfo updateJob(String uri, String id, String action, Map<String, Object> data,
            CronTriggerInfo trigger, URL callback) {
        JobInfo jobInfo = new JobInfo();
        jobInfo.setName(id);
        jobInfo.setAction(action);
        jobInfo.setData(data);
        jobInfo.setCronTrigger(trigger);
        return updateJob(uri, jobInfo);
    }
    
    @Override
    public abstract List<String> getJobNames(String uri);
    
    @Override
    public abstract JobInfo getJobInfo(String uri, String jobName);
    
    @Override
    public abstract JobInfo updateJob(String uri, JobInfo jobInfo);
    
    @Override
    public abstract void deleteJob(String uri, String jobName);
    
    @Override
    public abstract JobInfo createJob(String uri, JobInfo jobInfo);
    
    public IdGenerator getIdGenerator() {
        return this.idGenerator;
    }
    
    public void setIdGenerator(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }
}
