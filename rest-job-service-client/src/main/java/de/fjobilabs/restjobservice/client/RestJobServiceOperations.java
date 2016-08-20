package de.fjobilabs.restjobservice.client;

import java.net.URL;
import java.util.List;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import de.fjobilabs.restjobservice.client.domain.CronTriggerInfo;
import de.fjobilabs.restjobservice.client.domain.JobInfo;
import de.fjobilabs.restjobservice.client.domain.SimpleTriggerInfo;

/**
 * Interface specifying the set of RestJobService operations. Implemented by
 * {@link RestTemplate}.
 * 
 * @author Felix Jordan
 * @since 14.08.2016 - 00:02:09
 * @version 1.0
 */
public interface RestJobServiceOperations {
    
    public JobInfo createJob(String uri, String action);
    
    public JobInfo createJob(String uri, String action, Map<String, Object> data);
    
    public JobInfo createJob(String uri, String action, SimpleTriggerInfo trigger);
    
    public JobInfo createJob(String uri, String action, Map<String, Object> data, SimpleTriggerInfo trigger);
    
    public JobInfo createJob(String uri, String action, CronTriggerInfo trigger);
    
    public JobInfo createJob(String uri, String action, Map<String, Object> data, CronTriggerInfo trigger);
    
    public JobInfo createJob(String uri, String action, URL callback);
    
    public JobInfo createJob(String uri, String action, Map<String, Object> data, URL callback);
    
    public JobInfo createJob(String uri, String action, SimpleTriggerInfo trigger, URL callback);
    
    public JobInfo createJob(String uri, String action, Map<String, Object> data, SimpleTriggerInfo trigger,
            URL callback);
            
    public JobInfo createJob(String uri, String action, CronTriggerInfo trigger, URL callback);
    
    public JobInfo createJob(String uri, String action, Map<String, Object> data, CronTriggerInfo trigger,
            URL callback);
            
    public JobInfo createJob(String uri, String id, String action);
    
    public JobInfo createJob(String uri, String id, String action, Map<String, Object> data);
    
    public JobInfo createJob(String uri, String id, String action, SimpleTriggerInfo trigger);
    
    public JobInfo createJob(String uri, String id, String action, Map<String, Object> data,
            SimpleTriggerInfo trigger);
            
    public JobInfo createJob(String uri, String id, String action, CronTriggerInfo trigger);
    
    public JobInfo createJob(String uri, String id, String action, Map<String, Object> data,
            CronTriggerInfo trigger);
            
    public JobInfo createJob(String uri, String id, String action, URL callback);
    
    public JobInfo createJob(String uri, String id, String action, Map<String, Object> data, URL callback);
    
    public JobInfo createJob(String uri, String id, String action, SimpleTriggerInfo trigger, URL callback);
    
    public JobInfo createJob(String uri, String id, String action, Map<String, Object> data,
            SimpleTriggerInfo trigger, URL callback);
            
    public JobInfo createJob(String uri, String id, String action, CronTriggerInfo trigger, URL callback);
    
    public JobInfo createJob(String uri, String id, String action, Map<String, Object> data,
            CronTriggerInfo trigger, URL callback);
    
    public JobInfo updateJob(String uri, String id, String action);
    
    public JobInfo updateJob(String uri, String id, String action, Map<String, Object> data);
    
    public JobInfo updateJob(String uri, String id, String action, SimpleTriggerInfo trigger);
    
    public JobInfo updateJob(String uri, String id, String action, Map<String, Object> data,
            SimpleTriggerInfo trigger);
            
    public JobInfo updateJob(String uri, String id, String action, CronTriggerInfo trigger);
    
    public JobInfo updateJob(String uri, String id, String action, Map<String, Object> data,
            CronTriggerInfo trigger);
            
    public JobInfo updateJob(String uri, String id, String action, URL callback);
    
    public JobInfo updateJob(String uri, String id, String action, Map<String, Object> data, URL callback);
    
    public JobInfo updateJob(String uri, String id, String action, SimpleTriggerInfo trigger, URL callback);
    
    public JobInfo updateJob(String uri, String id, String action, Map<String, Object> data,
            SimpleTriggerInfo trigger, URL callback);
            
    public JobInfo updateJob(String uri, String id, String action, CronTriggerInfo trigger, URL callback);
    
    public JobInfo updateJob(String uri, String id, String action, Map<String, Object> data,
            CronTriggerInfo trigger, URL callback);
    
    public List<String> getJobNames(String uri);
    
    public JobInfo getJobInfo(String uri, String jobName);
    
    public JobInfo updateJob(String uri, JobInfo jobInfo);
    
    public void deleteJob(String uri, String jobName);
    
    public JobInfo createJob(String uri, JobInfo jobInfo);
}
