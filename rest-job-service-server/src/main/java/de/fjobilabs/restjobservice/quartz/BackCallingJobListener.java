package de.fjobilabs.restjobservice.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import de.fjobilabs.restjobservice.domain.JobCallbackData;

/**
 * @author Felix Jordan
 * @since 12.08.2016 - 23:12:03
 * @version 1.0
 */
public class BackCallingJobListener implements JobListener {
    
    private static final Logger logger = LoggerFactory.getLogger(BackCallingJobListener.class);
    
    private String name;
    
    @Override
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        JobKey jobKey = context.getJobDetail().getKey();
        
        JobCallbackData<Object> data = new JobCallbackData<>();
        data.setJobName(jobKey.getName());
        data.setResult(context.getResult());
        data.setException(jobException);
        
        String callbackUrl = context.getMergedJobDataMap().getString("job-callback-url");
        if (callbackUrl == null) {
            logger.info("Job has no callback");
            return;
        }
        logger.info("Calling back to url " + callbackUrl);
        
        RestTemplate restTemplate = new RestTemplate();
        
        restTemplate.postForLocation(callbackUrl, data);
    }
}
