package de.fjobilabs.restjobservice.client;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.fjobilabs.restjobservice.client.domain.JobInfo;
import de.fjobilabs.restjobservice.client.exception.RestJobServiceTemplateException;
import de.fjobilabs.restjobservice.client.util.RestJobServiceExceptionFactory;

import de.fjobilabs.springutils.web.client.RestResourceTemplate;
import de.fjobilabs.springutils.web.resources.RestResource;
import de.fjobilabs.springutils.web.resources.exception.RestResourceExceptionFactory;

/**
 * @author Felix Jordan
 * @since 14.08.2016 - 00:08:10
 * @version 1.0
 */
public class RestJobServiceTemplate extends AbstractRestJobServiceOperations {
    
    private RestResourceTemplate restTemplate = new RestResourceTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();
    private RestResourceExceptionFactory restResourceExceptionFactory = RestJobServiceExceptionFactory
            .getInstance();
            
    @Override
    @SuppressWarnings("unchecked")
    public List<String> getJobNames(String uri) {
        RestResource response = this.restTemplate.getForResource(uri);
        if (!response.getStatus().equals(RestResource.SUCCESS)) {
            tryThrowRestJobException(response);
            throw new RestJobServiceTemplateException("Failed to get job names");
        }
        return (List<String>) response.getData();
    }
    
    @Override
    public JobInfo getJobInfo(String uri, String jobName) {
        RestResource response = this.restTemplate.getForResource(uri + "/{job-id}", jobName);
        if (!response.getStatus().equals(RestResource.SUCCESS)) {
            tryThrowRestJobException(response);
            throw new RestJobServiceTemplateException("Failed to get info for job " + jobName);
        }
        return this.objectMapper.convertValue(response.getData(), JobInfo.class);
    }
    
    @Override
    public JobInfo updateJob(String uri, JobInfo jobInfo) {
        RestResource response = this.restTemplate.putForResource(uri + "/{job-id}", jobInfo,
                jobInfo.getName());
        if (!response.getStatus().equals(RestResource.SUCCESS)) {
            tryThrowRestJobException(response);
            throw new RestJobServiceTemplateException("Failed to put job " + jobInfo);
        }
        return this.objectMapper.convertValue(response.getData(), JobInfo.class);
    }
    
    @Override
    public void deleteJob(String uri, String jobName) {
        RestResource response = this.restTemplate.deleteForResource(uri + "/{job-id}", jobName);
        if (!response.getStatus().equals(RestResource.SUCCESS)) {
            tryThrowRestJobException(response);
            throw new RestJobServiceTemplateException("Failed to delete job " + jobName);
        }
    }
    
    @Override
    public JobInfo createJob(String uri, JobInfo jobInfo) {
        RestResource response = this.restTemplate.postForResource(uri, jobInfo);
        if (!response.getStatus().equals(RestResource.SUCCESS)) {
            tryThrowRestJobException(response);
            throw new RestJobServiceTemplateException(
                    "Failed to create job " + jobInfo + " (" + response.getData() + ")");
        }
        return this.objectMapper.convertValue(response.getData(), JobInfo.class);
    }
    
    private void tryThrowRestJobException(RestResource response) {
        int code = response.getCode();
        if (code == 0) {
            return;
        }
        throw this.restResourceExceptionFactory.createException(code,
                response.getData().toString());
    }
    
    public RestResourceTemplate getRestTemplate() {
        return restTemplate;
    }
    
    public void setRestTemplate(RestResourceTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
    
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    
    public RestResourceExceptionFactory getRestResourceExceptionFactory() {
        return restResourceExceptionFactory;
    }
    
    public void setRestResourceExceptionFactory(
            RestResourceExceptionFactory restResourceExceptionFactory) {
        this.restResourceExceptionFactory = restResourceExceptionFactory;
    }
}
