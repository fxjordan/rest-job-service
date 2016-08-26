package de.fjobilabs.restjobservice.service;

import java.util.List;

import de.fjobilabs.restjobservice.domain.JobInfo;

/**
 * @author Felix Jordan
 * @since 26.08.2016 - 16:14:29
 * @version 1.0
 */
public interface JobService {
    
    public List<String> getJobNames();
    
    public JobInfo getJob(String name);
    
    public void createJob(JobInfo jobInfo);
    
    public void updateJob(JobInfo jobInfo);
    
    public void deleteJob(String name);
}
