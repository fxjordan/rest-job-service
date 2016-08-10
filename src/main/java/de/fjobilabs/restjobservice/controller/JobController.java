package de.fjobilabs.restjobservice.controller;

import java.util.List;

import org.springframework.util.IdGenerator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.fjobilabs.restjobservice.domain.JobInfo;
import de.fjobilabs.restjobservice.service.JobService;
import de.fjobilabs.util.web.RestResource;

/**
 * @author Felix Jordan
 * @since 01.08.2016 - 23:37:59
 * @version 1.0
 */
@RestController
public class JobController {
    
    private JobService jobService;
    private IdGenerator idGenerator;
    private String jobsRootPath;
    
    @RequestMapping(path = "#{@jobController.jobsRootPath}/{group}/{name}", method = RequestMethod.GET)
    public RestResource getJob(@PathVariable("group") String group,
            @PathVariable("name") String name) {
        String requestId = idGenerator.generateId().toString();
        RestResource resource = new RestResource();
        resource.setRequestId(requestId);
        
        JobInfo job = jobService.getJob(group, name);
        if (job == null) {
            resource.setStatus(RestResource.FAIL);
            resource.setData("No job with group '" + group + "' and name '" + name + "' exists");
            return resource;
        }
        
        resource.setStatus(RestResource.SUCESS);
        resource.setData(job);
        return resource;
    }
    
    /**
     * Lists all groups
     * 
     * @return
     */
    @RequestMapping(path = "#{@jobController.jobsRootPath}", method = RequestMethod.GET)
    public RestResource getGoupNames() {
        String requestId = idGenerator.generateId().toString();
        
        List<String> jobs = jobService.getGroupNames();
        
        RestResource resource = new RestResource();
        resource.setRequestId(requestId);
        resource.setStatus(RestResource.SUCESS);
        resource.setData(jobs);
        return resource;
    }
    
    @RequestMapping(path = "#{@jobController.jobsRootPath}/{group}")
    public RestResource getJobNames(@PathVariable("group") String group) {
        String requestId = idGenerator.generateId().toString();
        RestResource resource = new RestResource();
        resource.setRequestId(requestId);
        
        List<String> jobs = jobService.getJobNames(group);
        if (jobs.isEmpty()) {
            resource.setStatus(RestResource.FAIL);
            resource.setData("No group with name '" + group + "' exists");
            return resource;
        }
        
        resource.setStatus(RestResource.SUCESS);
        resource.setData(jobs);
        return resource;
    }
    
    /*
     * TODO Implement the creation process of a job with a POST-PUT combination,
     * because of the idempotence of the HTTP-POST verb.
     */
    @RequestMapping(path = "#{@jobController.jobsRootPath}", method = RequestMethod.POST)
    public RestResource createJob(@RequestBody JobInfo jobInfo) {
        String requestId = idGenerator.generateId().toString();
        RestResource resource = new RestResource();
        resource.setRequestId(requestId);
        
        jobService.createJob(jobInfo);
        
        resource.setStatus(RestResource.SUCESS);
        return resource;
    }
    
    @RequestMapping(path = "#{@jobController.jobsRootPath}/{group}/{job}", method = RequestMethod.PUT)
    public RestResource updateJob(@RequestBody JobInfo jobInfo) {
        String requestId = idGenerator.generateId().toString();
        RestResource resource = new RestResource();
        resource.setRequestId(requestId);
        
        jobService.updateJob(jobInfo);
        
        resource.setStatus(RestResource.SUCESS);
        return resource;
    }
    
    @RequestMapping(path = "#{@jobController.jobsRootPath}/{group}/{name}", method = RequestMethod.DELETE)
    public RestResource deleteJob(@PathVariable("group") String jobGroup,
            @PathVariable("name") String jobName) {
        String requestId = idGenerator.generateId().toString();
        RestResource resource = new RestResource();
        resource.setRequestId(requestId);
        
        jobService.deleteJob(jobGroup, jobName);
        
        resource.setStatus(RestResource.SUCESS);
        return resource;
    }
    
    public JobService getJobService() {
        return jobService;
    }
    
    public void setJobService(JobService jobService) {
        this.jobService = jobService;
    }
    
    public IdGenerator getIdGenerator() {
        return idGenerator;
    }
    
    public void setIdGenerator(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }
    
    public String getJobsRootPath() {
        return jobsRootPath;
    }
    
    public void setJobsRootPath(String jobsRootPath) {
        this.jobsRootPath = jobsRootPath;
    }
}
