package de.fjobilabs.restjobservice.client;

import java.util.Map;

import de.fjobilabs.restjobservice.callbackserver.callback.JobCallback;
import de.fjobilabs.restjobservice.client.domain.CronTriggerInfo;
import de.fjobilabs.restjobservice.client.domain.JobInfo;
import de.fjobilabs.restjobservice.client.domain.SimpleTriggerInfo;

/**
 * Extends the {@link RestJobServiceOperations} by methods, which can use
 * {@link JobCallback} classes as callback instead of {@code URL}s.<br>
 * 
 * @author Felix Jordan
 * @since 17.08.2016 - 23:33:01
 * @version 1.0
 */
public interface CallbackRestJobServiceOperations extends RestJobServiceOperations {
    
    public JobInfo createJob(String uri, String action, Class<? extends JobCallback> callback);
    
    public JobInfo createJob(String uri, String action, Map<String, Object> data,
            Class<? extends JobCallback> callback);
            
    public JobInfo createJob(String uri, String action, SimpleTriggerInfo trigger,
            Class<? extends JobCallback> callback);
            
    public JobInfo createJob(String uri, String action, Map<String, Object> data, SimpleTriggerInfo trigger,
            Class<? extends JobCallback> callback);
            
    public JobInfo createJob(String uri, String action, CronTriggerInfo trigger,
            Class<? extends JobCallback> callback);
            
    public JobInfo createJob(String uri, String action, Map<String, Object> data, CronTriggerInfo trigger,
            Class<? extends JobCallback> callback);
            
    public JobInfo createJob(String uri, String id, String action, Class<? extends JobCallback> callback);
    
    public JobInfo createJob(String uri, String id, String action, Map<String, Object> data,
            Class<? extends JobCallback> callback);
            
    public JobInfo createJob(String uri, String id, String action, SimpleTriggerInfo trigger,
            Class<? extends JobCallback> callback);
            
    public JobInfo createJob(String uri, String id, String action, Map<String, Object> data,
            SimpleTriggerInfo trigger, Class<? extends JobCallback> callback);
            
    public JobInfo createJob(String uri, String id, String action, CronTriggerInfo trigger,
            Class<? extends JobCallback> callback);
            
    public JobInfo createJob(String uri, String id, String action, Map<String, Object> data,
            CronTriggerInfo trigger, Class<? extends JobCallback> callback);
            
    public JobInfo updateJob(String uri, String id, String action, Class<? extends JobCallback> callback);
    
    public JobInfo updateJob(String uri, String id, String action, Map<String, Object> data,
            Class<? extends JobCallback> callback);
            
    public JobInfo updateJob(String uri, String id, String action, SimpleTriggerInfo trigger,
            Class<? extends JobCallback> callback);
            
    public JobInfo updateJob(String uri, String id, String action, Map<String, Object> data,
            SimpleTriggerInfo trigger, Class<? extends JobCallback> callback);
            
    public JobInfo updateJob(String uri, String id, String action, CronTriggerInfo trigger,
            Class<? extends JobCallback> callback);
            
    public JobInfo updateJob(String uri, String id, String action, Map<String, Object> data,
            CronTriggerInfo trigger, Class<? extends JobCallback> callback);
}
