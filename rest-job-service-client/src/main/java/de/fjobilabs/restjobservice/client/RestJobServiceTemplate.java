package de.fjobilabs.restjobservice.client;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.fjobilabs.restjobservice.client.domain.JobInfo;
import de.fjobilabs.restjobservice.client.exception.RestJobServiceTemplateException;

import de.fjobilabs.springutils.web.client.RestResourceTemplate;
import de.fjobilabs.springutils.web.resources.RestResource;

/**
 * @author Felix Jordan
 * @since 14.08.2016 - 00:08:10
 * @version 1.0
 */
public class RestJobServiceTemplate extends AbstractRestJobServiceOperations {
    
    private RestResourceTemplate restTemplate = new RestResourceTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();
    private String webServiceRootPath;
    
    public RestJobServiceTemplate(String webServiceRootPath) {
        this.webServiceRootPath = webServiceRootPath;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<String> getJobNames() {
        RestResource response = this.restTemplate.getForResource(this.webServiceRootPath);
        if (!response.getStatus().equals(RestResource.SUCCESS)) {
            throw new RestJobServiceTemplateException("Failed to get job names");
        }
        return (List<String>) response.getData();
    }
    
    @Override
    public JobInfo getJobInfo(String jobName) {
        RestResource response = this.restTemplate
                .getForResource(this.webServiceRootPath + "/{job-id}", jobName);
        if (!response.getStatus().equals(RestResource.SUCCESS)) {
            throw new RestJobServiceTemplateException("Failed to get info for job " + jobName);
        }
        return this.objectMapper.convertValue(response.getData(), JobInfo.class);
    }
    
    @Override
    public JobInfo updateJob(JobInfo jobInfo) {
        RestResource response = this.restTemplate
                .putForResource(this.webServiceRootPath + "/{job-id}", jobInfo, jobInfo.getName());
        if (!response.getStatus().equals(RestResource.SUCCESS)) {
            throw new RestJobServiceTemplateException("Failed to put job " + jobInfo);
        }
        return this.objectMapper.convertValue(response.getData(), JobInfo.class);
    }
    
    @Override
    public void deleteJob(String jobName) {
        RestResource response = this.restTemplate
                .deleteForResource(this.webServiceRootPath + "/{job-id}", jobName);
        if (!response.getStatus().equals(RestResource.SUCCESS)) {
            throw new RestJobServiceTemplateException("Failed to delete job " + jobName);
        }
    }
    
    @Override
    public JobInfo createJob(JobInfo jobInfo) {
        RestResource response = this.restTemplate.postForResource(this.webServiceRootPath, jobInfo);
        if (!response.getStatus().equals(RestResource.SUCCESS)) {
            throw new RestJobServiceTemplateException("Failed to create job " + jobInfo +
                    " (" + response.getData() + ")");
        }
        return this.objectMapper.convertValue(response.getData(), JobInfo.class);
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
    
    public String getWebServiceRootPath() {
        return webServiceRootPath;
    }
    
    public void setWebServiceRootPath(String webServiceRootPath) {
        this.webServiceRootPath = webServiceRootPath;
    }
}
