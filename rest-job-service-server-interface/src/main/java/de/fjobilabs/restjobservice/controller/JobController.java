package de.fjobilabs.restjobservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.fjobilabs.restjobservice.domain.JobInfo;
import de.fjobilabs.restjobservice.service.JobService;
import de.fjobilabs.springutils.web.requestidmanager.service.RequestIdService;
import de.fjobilabs.springutils.web.resources.RestResource;

/**
 * @author Felix Jordan
 * @since 01.08.2016 - 23:37:59
 * @version 1.0
 */
@RestController
public class JobController {
    
    @Autowired
    private RequestIdService requestIdService;
    
    private JobService jobService;
    private String jobsRootPath;
    
    @RequestMapping(path = "#{@jobController.jobsRootPath}/{name}", method = RequestMethod.GET)
    public RestResource getJob(@PathVariable("name") String name) {
        RestResource resource = new RestResource();
        resource.setRequestId(this.requestIdService.getRequestId());
        
        JobInfo job = this.jobService.getJob(name);
        
        resource.setStatus(RestResource.SUCCESS);
        resource.setData(job);
        return resource;
    }
    
    /**
     * Lists all jobs
     * 
     * @return
     */
    @RequestMapping(path = "#{@jobController.jobsRootPath}")
    public RestResource getJobNames() {
        RestResource resource = new RestResource();
        resource.setRequestId(this.requestIdService.getRequestId());
        
        List<String> jobs = jobService.getJobNames();
        
        resource.setStatus(RestResource.SUCCESS);
        resource.setData(jobs);
        return resource;
    }
    
    /*
     * TODO Implement the creation process of a job with a POST-PUT combination,
     * because of the idempotence of the HTTP-POST verb.
     */
    @RequestMapping(path = "#{@jobController.jobsRootPath}", method = RequestMethod.POST)
    public RestResource createJob(@RequestBody JobInfo jobInfo) {
        RestResource resource = new RestResource();
        resource.setRequestId(this.requestIdService.getRequestId());
        
        jobService.createJob(jobInfo);
        JobInfo createdJobInfo = jobService.getJob(jobInfo.getName());
        
        resource.setStatus(RestResource.SUCCESS);
        resource.setData(createdJobInfo);
        return resource;
    }
    
    @RequestMapping(path = "#{@jobController.jobsRootPath}/{group}/{job}", method = RequestMethod.PUT)
    public RestResource updateJob(@RequestBody JobInfo jobInfo) {
        RestResource resource = new RestResource();
        resource.setRequestId(this.requestIdService.getRequestId());
        
        jobService.updateJob(jobInfo);
        JobInfo updatedJobInfo = jobService.getJob(jobInfo.getName());
        
        resource.setStatus(RestResource.SUCCESS);
        resource.setData(updatedJobInfo);
        return resource;
    }
    
    @RequestMapping(path = "#{@jobController.jobsRootPath}/{name}", method = RequestMethod.DELETE)
    public RestResource deleteJob(@PathVariable("name") String jobName) {
        RestResource resource = new RestResource();
        resource.setRequestId(this.requestIdService.getRequestId());
        
        jobService.deleteJob(jobName);
        
        resource.setStatus(RestResource.SUCCESS);
        return resource;
    }
    
    public JobService getJobService() {
        return jobService;
    }
    
    public void setJobService(JobService jobService) {
        this.jobService = jobService;
    }
    
    public String getJobsRootPath() {
        return jobsRootPath;
    }
    
    public void setJobsRootPath(String jobsRootPath) {
        this.jobsRootPath = jobsRootPath;
    }
}
