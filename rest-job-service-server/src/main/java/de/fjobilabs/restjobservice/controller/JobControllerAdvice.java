package de.fjobilabs.restjobservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import de.fjobilabs.restjobservice.exception.InternalSchedulerException;
import de.fjobilabs.restjobservice.exception.InvalidJobTriggerException;
import de.fjobilabs.restjobservice.exception.JobNotFoundException;
import de.fjobilabs.restjobservice.exception.MalformedCronExpressionExeption;
import de.fjobilabs.restjobservice.exception.UnknownJobActionException;
import de.fjobilabs.springutils.web.requestidmanager.service.RequestIdService;
import de.fjobilabs.springutils.web.resources.RestResource;

/**
 * @author Felix Jordan
 * @since 15.08.2016 - 22:36:07
 * @version 1.0
 */
@RestControllerAdvice
public class JobControllerAdvice {
    
    @Autowired
    private RequestIdService requestIdService;
    
    @ExceptionHandler(UnknownJobActionException.class)
    @ResponseStatus(code=HttpStatus.BAD_REQUEST)
    public RestResource unknownJobAction(UnknownJobActionException exception) {
        RestResource response = new RestResource();
        response.setRequestId(this.requestIdService.getRequestId());
        response.setStatus(RestResource.FAIL);
        response.setData(exception.getMessage());
        return response;
    }
    
    @ExceptionHandler(JobNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResource jobNotFound(JobNotFoundException exception) {
        RestResource response = new RestResource();
        response.setRequestId(this.requestIdService.getRequestId());
        response.setStatus(RestResource.FAIL);
        response.setData(exception.getMessage());
        return response;
    }
    
    @ExceptionHandler(MalformedCronExpressionExeption.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResource malformedCronExpression(MalformedCronExpressionExeption exception) {
        RestResource response = new RestResource();
        response.setRequestId(this.requestIdService.getRequestId());
        response.setStatus(RestResource.FAIL);
        response.setData(exception.getMessage());
        return response;
    }
    
    @ExceptionHandler(InvalidJobTriggerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResource invalidJobTrigger(InvalidJobTriggerException exception) {
        RestResource response = new RestResource();
        response.setRequestId(this.requestIdService.getRequestId());
        response.setStatus(RestResource.FAIL);
        response.setData(exception.getMessage());
        return response;
    }
    
    @ExceptionHandler(InternalSchedulerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestResource internalSchedulerException(InternalSchedulerException exception) {
        RestResource response = new RestResource();
        response.setRequestId(this.requestIdService.getRequestId());
        response.setStatus(RestResource.ERROR);
        response.setData(exception.getMessage());
        return response;  }
}
