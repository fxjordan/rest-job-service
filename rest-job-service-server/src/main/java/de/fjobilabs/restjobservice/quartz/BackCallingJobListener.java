package de.fjobilabs.restjobservice.quartz;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.JobListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.client.RestClientException;

import de.fjobilabs.restjobservice.domain.ExceptionInfo;
import de.fjobilabs.restjobservice.domain.JobCallbackData;
import de.fjobilabs.restjobservice.exception.ExceptionHandlerException;
import de.fjobilabs.restjobservice.service.ServerJobService;
import de.fjobilabs.springutils.web.client.RestResourceTemplate;
import de.fjobilabs.springutils.web.resources.RestResource;

/**
 * @author Felix Jordan
 * @since 12.08.2016 - 23:12:03
 * @version 1.0
 */
public class BackCallingJobListener implements JobListener {
    
    private static final Logger logger = LoggerFactory.getLogger(BackCallingJobListener.class);
    
    private String name;
    
    private RestResourceTemplate restResourceTemplate = new RestResourceTemplate();
    
    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
    }
    
    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
    }
    
    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        JobKey jobKey = context.getJobDetail().getKey();
        // Only handle jobs scheduled by the RestJobService
        if (!jobKey.getGroup().equals(ServerJobService.JOB_GROUP)) {
            return;
        }
        
        boolean refireJob = false;
        
        Throwable exception = null;
        if (jobException != null) {
            exception = jobException.getUnderlyingException();
            refireJob = jobException.refireImmediately();
        }
        
        JobCallbackData callbackData = new JobCallbackData();
        callbackData.setJobName(jobKey.getName());
        
        Job jobInstance = context.getJobInstance();
        if (jobInstance instanceof RestJob) {
            RestJob restJob = (RestJob) jobInstance;
            if (exception == null) {
                exception = restJob.getException();
            }
            applyDataFromRestJob(callbackData, context.getResult(), exception, restJob);
        } else {
            applyDataFromDefaultJob(callbackData, context.getResult(), exception);
        }
        
        
        
        if (exception == null) {
            logger.debug("Job " + jobKey + " executed successfully");
        } else {
            logger.warn("Job " + jobKey + " failed to execute (result=" + callbackData + ")");
        }
        
        String callbackUrl = context.getMergedJobDataMap().getString("job-callback-url");
        if (callbackUrl == null) {
            logger.debug("Job has no callback");
            return;
        } else if (refireJob) {
            logger.debug("Callback is not called. Job is refiring immediately");
        }
        logger.debug("Calling back to url " + callbackUrl);
        
        RestResource response;
        try {
            response = this.restResourceTemplate.putForResource(callbackUrl, callbackData);
        } catch (RestClientException e) {
            logger.error("Failed to call job callback for job " + jobKey, e);
            return;
        }
        
        switch (response.getStatus()) {
        case RestResource.SUCCESS:
            logger.debug("Sucessfully called back");
            break;
        case RestResource.FAIL:
        case RestResource.ERROR:
            logger.error("Error while calling back (" + response.getData() + ")");
            break;
        }
    }
    
    private void applyDataFromRestJob(JobCallbackData data, Object result, Throwable exception,
            RestJob restJob) {
        if (exception != null) {
            if (!handleException(data, exception, restJob)) {
                logger.error("Unhandled job exception:", exception);
                data.setStatus(JobCallbackData.ERROR);
                data.setResult(createExceptionInfo(exception));
            }
        } else {
            data.setStatus(JobCallbackData.SUCCESS);
            data.setResult(result);
        }
    }
    
    private boolean handleException(JobCallbackData data, Throwable exception,
            Object exceptionHandler) {
        Method[] methods = exceptionHandler.getClass().getMethods();
        for (Method method : methods) {
            if (canHandleException(method, exception)) {
                handleException(data, method, exceptionHandler, exception);
                return true;
            }
        }
        return false;
    }
    
    private void handleException(JobCallbackData data, Method method, Object handler,
            Throwable exception) {
        JobCallback callbackAnnotation = method.getDeclaredAnnotation(JobCallback.class);
        if (callbackAnnotation != null) {
            data.setStatus(callbackAnnotation.status());
            data.setResult(callbackAnnotation.message());
        }
        Object result;
        try {
            if (method.getParameterCount() == 0) {
                result = method.invoke(handler);
            } else {
                result = method.invoke(handler, exception);
            }
        } catch (IllegalAccessException e) {
            throw new ExceptionHandlerException(e);
        } catch (IllegalArgumentException e) {
            throw new ExceptionHandlerException(e);
        } catch (InvocationTargetException e) {
            throw new ExceptionHandlerException(e);
        }
        if (result != null) {
            data.setResult(result);
        }
        if (data.getResult() == null || data.getResult().equals("")) {
            data.setResult(exception.getMessage());
        }
    }
    
    private boolean canHandleException(Method method, Throwable exception) {
        JobExceptionHandler handlerAnnotation = method.getAnnotation(JobExceptionHandler.class);
        if (handlerAnnotation == null) {
            return false;
        }
        Class<? extends Throwable>[] exceptions = handlerAnnotation.value();
        for (Class<? extends Throwable> handledException : exceptions) {
            if (handledException.equals(exception.getClass())) {
                return true;
            }
        }
        return false;
    }
    
    private void applyDataFromDefaultJob(JobCallbackData data, Object result, Throwable exception) {
        if (exception == null) {
            data.setStatus(JobCallbackData.SUCCESS);
            data.setResult(result);
        } else {
            data.setStatus(JobCallbackData.ERROR);
            data.setResult(createExceptionInfo(exception));
        }
    }
    
    private ExceptionInfo createExceptionInfo(Throwable throwable) {
        ExceptionInfo exceptionInfo = new ExceptionInfo();
        exceptionInfo.setException(throwable.getClass().getName());
        exceptionInfo.setMessage(throwable.getMessage());
        return exceptionInfo;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public RestResourceTemplate getRestResourceTemplate() {
        return restResourceTemplate;
    }
    
    public void setRestResourceTemplate(RestResourceTemplate restResourceTemplate) {
        this.restResourceTemplate = restResourceTemplate;
    }
}
