package de.fjobilabs.restjobservice.callbackserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.IdGenerator;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import de.fjobilabs.restjobservice.callbackserver.callback.JobCallback;
import de.fjobilabs.restjobservice.callbackserver.callback.JobCallbackFactory;
import de.fjobilabs.restjobservice.callbackserver.controller.JobCallbackController;
import de.fjobilabs.restjobservice.callbackserver.domain.CallbackInfo;
import de.fjobilabs.restjobservice.callbackserver.domain.JobCallbackData;
import de.fjobilabs.restjobservice.callbackserver.exceptions.CallbackExecutionException;
import de.fjobilabs.restjobservice.callbackserver.exceptions.CallbackNotFoundException;
import de.fjobilabs.restjobservice.callbackserver.repository.CallbackInfoRepository;

/**
 * @author Felix Jordan
 * @since 16.08.2016 - 20:45:16
 * @version 1.0
 */
public class JobCallbackService {
    
    @Autowired
    private CallbackInfoRepository callbackInfoRepository;
    
    private IdGenerator idGenerator;
    private JobCallbackFactory jobCallbackFactory;
    private String callbacksRootPath;
    
    public List<CallbackInfo> getJobCallbacks() {
        return this.callbackInfoRepository.findAll();
    }
    
    public CallbackInfo getCallback(String id) {
        CallbackInfo callbackInfo = this.callbackInfoRepository.findOne(id);
        if (callbackInfo == null) {
            throw new CallbackNotFoundException("Callback " + id + "does not exist");
        }
        return callbackInfo;
    }
    
    public String getCallbackURL(String id) {
        CallbackInfo callbackInfo = this.callbackInfoRepository.findOne(id);
        if (callbackInfo == null) {
            throw new CallbackNotFoundException("Callback " + id + "does not exist");
        }
        
        return MvcUriComponentsBuilder.fromController(JobCallbackController.class)
                .path(callbacksRootPath).path("/").path(id).build().toUriString();
    }
    
    public CallbackInfo createCallback(Class<? extends JobCallback> callbackClass) {
        CallbackInfo callbackInfo = new CallbackInfo();
        callbackInfo.setId(this.idGenerator.generateId().toString());
        callbackInfo.setCallbackClass(callbackClass.getName());
        callbackInfo.setState(CallbackInfo.WAITING);
        return this.callbackInfoRepository.insert(callbackInfo);
    }
    
    public CallbackInfo invokeCallback(String id, JobCallbackData callbackData) {
        CallbackInfo callbackInfo = this.callbackInfoRepository.findOne(id);
        if (callbackInfo == null) {
            throw new CallbackNotFoundException("Callback " + id + "does not exist");
        }
        if (!callbackInfo.getState().equals(CallbackInfo.WAITING)) {
            return callbackInfo;
        }
        
        JobCallback callback = this.jobCallbackFactory.createJobCallback(callbackInfo);
        try {
            callback.execute(callbackData);
        } catch (Exception e) {
            callbackInfo.setState(CallbackInfo.ERROR);
            this.callbackInfoRepository.save(callbackInfo);
            throw new CallbackExecutionException("Exception while executing callback " + id, e);
        }
        
        callbackInfo.setState(CallbackInfo.CALLED);
        return this.callbackInfoRepository.save(callbackInfo);
    }
    
    public CallbackInfoRepository getCallbackInfoRepository() {
        return callbackInfoRepository;
    }
    
    public void setCallbackInfoRepository(CallbackInfoRepository callbackInfoRepository) {
        this.callbackInfoRepository = callbackInfoRepository;
    }
    
    public IdGenerator getIdGenerator() {
        return idGenerator;
    }
    
    public void setIdGenerator(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }
    
    public JobCallbackFactory getJobCallbackFactory() {
        return jobCallbackFactory;
    }
    
    public void setJobCallbackFactory(JobCallbackFactory jobCallbackFactory) {
        this.jobCallbackFactory = jobCallbackFactory;
    }
    
    public String getCallbacksRootPath() {
        return callbacksRootPath;
    }
    
    public void setCallbacksRootPath(String callbacksRootPath) {
        this.callbacksRootPath = callbacksRootPath;
    }
}
