package de.fjobilabs.restjobservice.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import de.fjobilabs.restjobservice.callbackserver.callback.JobCallback;
import de.fjobilabs.restjobservice.callbackserver.domain.CallbackInfo;
import de.fjobilabs.restjobservice.callbackserver.service.JobCallbackService;
import de.fjobilabs.restjobservice.client.domain.CronTriggerInfo;
import de.fjobilabs.restjobservice.client.domain.JobInfo;
import de.fjobilabs.restjobservice.client.domain.SimpleTriggerInfo;
import de.fjobilabs.restjobservice.client.exception.CallbackRestJobServiceTemplateException;

/**
 * @author Felix Jordan
 * @since 17.08.2016 - 23:42:49
 * @version 1.0
 */
public class CallbackRestJobServiceTemplate extends RestJobServiceTemplate
        implements CallbackRestJobServiceOperations {
    
    private JobCallbackService jobCallbackService;
    
    public CallbackRestJobServiceTemplate(String webServiceRootPath,
            JobCallbackService jobCallbackService) {
        super(webServiceRootPath);
        this.jobCallbackService = jobCallbackService;
    }
    
    @Override
    public JobInfo createJob(String action, Class<? extends JobCallback> callback) {
        return createJob(action, createCallback(callback));
    }
    
    @Override
    public JobInfo createJob(String action, Map<String, Object> data,
            Class<? extends JobCallback> callback) {
        return createJob(action, data, createCallback(callback));
    }
    
    @Override
    public JobInfo createJob(String action, SimpleTriggerInfo trigger,
            Class<? extends JobCallback> callback) {
        return createJob(action, trigger, createCallback(callback));
    }
    
    @Override
    public JobInfo createJob(String action, Map<String, Object> data, SimpleTriggerInfo trigger,
            Class<? extends JobCallback> callback) {
        return createJob(action, data, trigger, createCallback(callback));
    }
    
    @Override
    public JobInfo createJob(String action, CronTriggerInfo trigger,
            Class<? extends JobCallback> callback) {
        return createJob(action, trigger, createCallback(callback));
    }
    
    @Override
    public JobInfo createJob(String action, Map<String, Object> data, CronTriggerInfo trigger,
            Class<? extends JobCallback> callback) {
        return createJob(action, data, trigger, createCallback(callback));
    }
    
    @Override
    public JobInfo createJob(String id, String action, Class<? extends JobCallback> callback) {
        return createJob(id, action, createCallback(callback));
    }
    
    @Override
    public JobInfo createJob(String id, String action, Map<String, Object> data,
            Class<? extends JobCallback> callback) {
        return createJob(id, action, data, createCallback(callback));
    }
    
    @Override
    public JobInfo createJob(String id, String action, SimpleTriggerInfo trigger,
            Class<? extends JobCallback> callback) {
        return createJob(id, action, trigger, createCallback(callback));
    }
    
    @Override
    public JobInfo createJob(String id, String action, Map<String, Object> data,
            SimpleTriggerInfo trigger, Class<? extends JobCallback> callback) {
        return createJob(id, action, data, trigger, createCallback(callback));
    }
    
    @Override
    public JobInfo createJob(String id, String action, CronTriggerInfo trigger,
            Class<? extends JobCallback> callback) {
        return createJob(id, action, trigger, createCallback(callback));
    }
    
    @Override
    public JobInfo createJob(String id, String action, Map<String, Object> data,
            CronTriggerInfo trigger, Class<? extends JobCallback> callback) {
        return createJob(id, action, data, trigger, createCallback(callback));
    }
    
    @Override
    public JobInfo updateJob(String id, String action, Class<? extends JobCallback> callback) {
        return updateJob(id, action, createCallback(callback));
    }
    
    @Override
    public JobInfo updateJob(String id, String action, Map<String, Object> data,
            Class<? extends JobCallback> callback) {
        return updateJob(id, action, data, createCallback(callback));
    }
    
    @Override
    public JobInfo updateJob(String id, String action, SimpleTriggerInfo trigger,
            Class<? extends JobCallback> callback) {
        return updateJob(id, action, trigger, createCallback(callback));
    }
    
    @Override
    public JobInfo updateJob(String id, String action, Map<String, Object> data,
            SimpleTriggerInfo trigger, Class<? extends JobCallback> callback) {
        return updateJob(id, action, data, trigger, createCallback(callback));
    }
    
    @Override
    public JobInfo updateJob(String id, String action, CronTriggerInfo trigger,
            Class<? extends JobCallback> callback) {
        return updateJob(id, action, trigger, createCallback(callback));
    }
    
    @Override
    public JobInfo updateJob(String id, String action, Map<String, Object> data,
            CronTriggerInfo trigger, Class<? extends JobCallback> callback) {
        return updateJob(id, action, data, trigger, createCallback(callback));
    }
    
    private URL createCallback(Class<? extends JobCallback> callbackClass) {
        CallbackInfo callbackInfo = this.jobCallbackService.createCallback(callbackClass);
        try {
            return new URL(this.jobCallbackService.getCallbackURL(callbackInfo.getId()));
        } catch (MalformedURLException e) {
            // should never happen
            throw new CallbackRestJobServiceTemplateException(
                    "Created callback url is not well formed", e);
        }
    }
    
    public JobCallbackService getJobCallbackService() {
        return jobCallbackService;
    }
    
    public void setJobCallbackService(JobCallbackService jobCallbackService) {
        this.jobCallbackService = jobCallbackService;
    }
}
