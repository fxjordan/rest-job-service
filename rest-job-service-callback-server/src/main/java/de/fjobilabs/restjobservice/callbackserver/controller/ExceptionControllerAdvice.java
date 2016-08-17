package de.fjobilabs.restjobservice.callbackserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import de.fjobilabs.restjobservice.callbackserver.exceptions.CallbackExecutionException;
import de.fjobilabs.restjobservice.callbackserver.exceptions.CallbackInstantiationException;
import de.fjobilabs.restjobservice.callbackserver.exceptions.CallbackNotFoundException;

import de.fjobilabs.springutils.requestidmanager.service.RequestIdService;
import de.fjobilabs.springutils.web.resources.RestResource;

/**
 * @author Felix Jordan
 * @since 17.08.2016 - 21:10:01
 * @version 1.0
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {
    
    @Autowired
    private RequestIdService requestIdService;
    
    @ExceptionHandler(CallbackNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResource callbackNotFound(CallbackNotFoundException exception) {
        RestResource response = new RestResource();
        response.setRequestId(this.requestIdService.getRequestId());
        response.setStatus(RestResource.FAIL);
        response.setData(exception.getMessage());
        return response;
    }
    
    @ExceptionHandler({CallbackInstantiationException.class, CallbackExecutionException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestResource callbackInstantiationFailed(Exception exception) {
        RestResource response = new RestResource();
        response.setRequestId(this.requestIdService.getRequestId());
        response.setStatus(RestResource.ERROR);
        response.setData(exception.getMessage());
        return response;
    }
}
