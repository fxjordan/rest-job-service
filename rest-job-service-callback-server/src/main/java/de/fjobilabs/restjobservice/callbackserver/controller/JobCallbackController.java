package de.fjobilabs.restjobservice.callbackserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.fjobilabs.restjobservice.callbackserver.domain.CallbackInfo;
import de.fjobilabs.restjobservice.callbackserver.domain.JobCallbackData;
import de.fjobilabs.restjobservice.callbackserver.service.JobCallbackService;
import de.fjobilabs.springutils.web.requestidmanager.service.RequestIdService;
import de.fjobilabs.springutils.web.resources.RestResource;

/**
 * @author Felix Jordan
 * @since 16.08.2016 - 20:34:57
 * @version 1.0
 */
@RestController
public class JobCallbackController {
    
    @Autowired
    private RequestIdService requestIdService;
    
    @Autowired
    private JobCallbackService jobCallbackService;
    
    private String callbacksRootPath;
    
    @RequestMapping(path="#{@jobCallbackController.callbacksRootPath}", method=RequestMethod.GET)
    public RestResource getCallbacks() {
        RestResource response = new RestResource();
        response.setRequestId(this.requestIdService.getRequestId());
        
        List<CallbackInfo> callbacks = this.jobCallbackService.getJobCallbacks();
        
        response.setStatus(RestResource.SUCCESS);
        response.setData(callbacks);
        return response;
    }
    
    @RequestMapping(path = "#{@jobCallbackController.callbacksRootPath}/{id}", method = RequestMethod.GET)
    public RestResource putCallbackData(@PathVariable("id") String id) {
        RestResource response = new RestResource();
        response.setRequestId(this.requestIdService.getRequestId());
        
        CallbackInfo callbackInfo = this.jobCallbackService.getCallback(id);
        
        response.setStatus(RestResource.SUCCESS);
        response.setData(callbackInfo);
        return response;
    }
    
    @RequestMapping(path = "#{@jobCallbackController.callbacksRootPath}/{id}", method = RequestMethod.PUT)
    public RestResource putCallbackData(@PathVariable("id") String id,
            @RequestBody JobCallbackData callbackData) {
        RestResource response = new RestResource();
        response.setRequestId(this.requestIdService.getRequestId());
        
        CallbackInfo callbackInfo = this.jobCallbackService.invokeCallback(id, callbackData);
        
        response.setStatus(RestResource.SUCCESS);
        response.setData(callbackInfo);
        return response;
    }
    
    public String getCallbacksRootPath() {
        return callbacksRootPath;
    }
    
    public void setCallbacksRootPath(String callbacksRootPath) {
        this.callbacksRootPath = callbacksRootPath;
    }
}
