package de.fjobilabs.restjobservice.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.matchers.GroupMatcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import de.fjobilabs.restjobservice.domain.CronTriggerInfo;
import de.fjobilabs.restjobservice.domain.JobInfo;
import de.fjobilabs.restjobservice.domain.SimpleTriggerInfo;
import de.fjobilabs.restjobservice.exception.InternalSchedulerException;
import de.fjobilabs.restjobservice.exception.InvalidJobTriggerException;
import de.fjobilabs.restjobservice.exception.JobNotFoundException;
import de.fjobilabs.restjobservice.exception.MalformedCronExpressionExeption;
import de.fjobilabs.restjobservice.exception.UnknownJobActionException;
import de.fjobilabs.restjobservice.quartz.CronExpressionFactoryBean;

/**
 * @author Felix Jordan
 * @since 01.08.2016 - 23:59:44
 * @version 1.0
 */
public class JobService {
    
    public static final String JOB_GROUP = "rest-jobs";
    
    private static final String DEFAULT_JOB_CONFIG_LOCATION = "jobs.properties";
    
    private static final Logger logger = LoggerFactory.getLogger(JobService.class);
    
    private Scheduler scheduler;
    private Map<String, Class<?>> jobClasses;
    private Resource jobConfigLocation;
    
    @PostConstruct
    public void init() throws IOException {
        if (this.jobClasses == null) {
            this.jobClasses = new HashMap<>();
        }
        
        Properties jobProperties;
        if (this.jobConfigLocation != null) {
            jobProperties = PropertiesLoaderUtils.loadProperties(this.jobConfigLocation);
        } else {
            jobProperties = PropertiesLoaderUtils.loadAllProperties(DEFAULT_JOB_CONFIG_LOCATION);
        }
        Enumeration<Object> elements = jobProperties.keys();
        while (elements.hasMoreElements()) {
            String jobAction = (String) elements.nextElement();
            String jobClassName = jobProperties.getProperty(jobAction);
            try {
                this.jobClasses.put(jobAction, Class.forName(jobClassName));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Job class '" + jobClassName + "' does not exist", e);
            }
            
        }
    }
    
    public JobInfo getJob(String name) {
        JobKey jobKey = new JobKey(name, JOB_GROUP);
        JobDetail jobDetail;
        try {
            jobDetail = this.scheduler.getJobDetail(jobKey);
        } catch (SchedulerException e) {
            throw new InternalSchedulerException(e);
        }
        if (jobDetail == null) {
            throw new JobNotFoundException("Job " + name + " does not exist");
        }
        JobInfo jobInfo = new JobInfo();
        jobInfo.setName(name);
        jobInfo.setAction(getActionFromJobClass(jobDetail.getJobClass()));
        jobInfo.setData(jobDetail.getJobDataMap());
        
        Trigger trigger = getJobTrigger(jobKey);
        if (trigger == null) {
            return jobInfo;
        }
        
        jobInfo.setLastExecutionTime(trigger.getPreviousFireTime());
        jobInfo.setNextExecutionTime(trigger.getNextFireTime());
        
        if (trigger instanceof SimpleTrigger) {
            jobInfo.setSimpleTrigger(createSimpleTriggerInfo((SimpleTrigger) trigger));
        } else if (trigger instanceof CronTrigger) {
            jobInfo.setCronTrigger(createCronTriggerInfo((CronTrigger) trigger));
        }
        
        return jobInfo;
    }
    
    public void createJob(JobInfo jobInfo) {
        JobDetail jobDetail = createJobDetail(jobInfo);
        jobDetail.getJobDataMap().put("job-callback-url", jobInfo.getCallback());
        
        Trigger trigger = createTrigger(jobInfo);
        
        try {
            this.scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            throw new InternalSchedulerException(e);
        }
    }
    
    public void updateJob(JobInfo jobInfo) {
        JobKey jobKey = new JobKey(jobInfo.getName(), JOB_GROUP);
        
        try {
            if (!this.scheduler.checkExists(jobKey)) {
                throw new JobNotFoundException("The job '" + jobKey + "' does not exist");
            }
        } catch (SchedulerException e) {
            throw new InternalSchedulerException(e);
        }
        
        JobDetail jobDetail = createJobDetail(jobInfo);
        jobDetail.getJobDataMap().put("job-callback-url", jobInfo.getCallback());
        
        Set<Trigger> triggers = new HashSet<>(1);
        triggers.add(createTrigger(jobInfo));
        
        try {
            this.scheduler.scheduleJob(jobDetail, triggers, true);
        } catch (SchedulerException e) {
            throw new InternalSchedulerException(e);
        }
    }
    
    public void deleteJob(String name) {
        try {
            scheduler.deleteJob(new JobKey(name, JOB_GROUP));
        } catch (SchedulerException e) {
            throw new InternalSchedulerException(e);
        }
    }
    
    public List<String> getJobNames() {
        Set<JobKey> jobKeys;
        try {
            jobKeys = this.scheduler.getJobKeys(GroupMatcher.groupEquals(JOB_GROUP));
        } catch (SchedulerException e) {
            throw new InternalSchedulerException(e);
        }
        List<String> jobNames = new ArrayList<>();
        for (JobKey jobKey : jobKeys) {
            jobNames.add(jobKey.getName());
        }
        return jobNames;
    }
    
    public Scheduler getScheduler() {
        return this.scheduler;
    }
    
    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
    
    public Map<String, Class<?>> getJobClasses() {
        return this.jobClasses;
    }
    
    public void setJobClasses(Map<String, Class<?>> jobClasses) {
        this.jobClasses = jobClasses;
    }
    
    private JobDetail createJobDetail(JobInfo jobInfo) {
        JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
        jobDetail.setName(jobInfo.getName());
        jobDetail.setGroup(JOB_GROUP);
        jobDetail.setJobClass(getJobClass(jobInfo.getAction()));
        Map<String, Object> jobData = jobInfo.getData();
        if (jobData != null) {
            jobDetail.setJobDataAsMap(jobData);
        }
        jobDetail.afterPropertiesSet();
        return jobDetail.getObject();
    }
    
    private Trigger createTrigger(JobInfo jobInfo) {
        if (jobInfo.getSimpleTrigger() != null) {
            if (jobInfo.getCronTrigger() != null) {
                throw new InvalidJobTriggerException(
                        "Job must either have a simple or a cron trigger");
            }
            return createSimpleTrigger(jobInfo);
        } else if (jobInfo.getCronTrigger() != null) {
            try {
                return createCronTrigger(jobInfo);
            } catch (ParseException e) {
                throw new MalformedCronExpressionExeption(e);
            }
        } else {
            throw new InvalidJobTriggerException("Job must either have a simple or a cron trigger");
        }
    }
    
    private SimpleTrigger createSimpleTrigger(JobInfo jobInfo) {
        SimpleTriggerInfo simpleTriggerInfo = jobInfo.getSimpleTrigger();
        
        SimpleTriggerFactoryBean simpleTrigger = new SimpleTriggerFactoryBean();
        simpleTrigger.setName(jobInfo.getName() + "-trigger");
        simpleTrigger.setGroup(JOB_GROUP);
        simpleTrigger.setRepeatCount(simpleTriggerInfo.getRepeatCount());
        simpleTrigger.setRepeatInterval(simpleTriggerInfo.getRepeatIntervall());
        simpleTrigger.setStartTime(simpleTriggerInfo.getStartTime());
        simpleTrigger.afterPropertiesSet();
        return simpleTrigger.getObject();
    }
    
    private CronTrigger createCronTrigger(JobInfo jobInfo) throws ParseException {
        CronTriggerFactoryBean cronTrigger = new CronTriggerFactoryBean();
        cronTrigger.setName(jobInfo.getName() + "-trigger");
        cronTrigger.setGroup(JOB_GROUP);
        cronTrigger.setCronExpression(
                createCronExpression(jobInfo.getCronTrigger()).getCronExpression());
        cronTrigger.afterPropertiesSet();
        return cronTrigger.getObject();
    }
    
    private CronExpression createCronExpression(CronTriggerInfo cronTrigger) throws ParseException {
        CronExpressionFactoryBean cronExpression = new CronExpressionFactoryBean();
        cronExpression.setSecond(cronTrigger.getSecond());
        cronExpression.setMinute(cronTrigger.getMinute());
        cronExpression.setHour(cronTrigger.getHour());
        cronExpression.setDayOfMonth(cronTrigger.getDayOfMonth());
        cronExpression.setMonth(cronTrigger.getMonth());
        cronExpression.setDayOfWeek(cronTrigger.getDayOfWeek());
        cronExpression.setYear(cronTrigger.getYear());
        cronExpression.afterPropertiesSet();
        return cronExpression.getObject();
    }
    
    private Class<?> getJobClass(String action) {
        Class<?> jobClass = this.jobClasses.get(action);
        if (jobClass == null) {
            throw new UnknownJobActionException("Unknown job action '" + action + "'");
        }
        return jobClass;
    }
    
    private SimpleTriggerInfo createSimpleTriggerInfo(SimpleTrigger trigger) {
        SimpleTriggerInfo triggerInfo = new SimpleTriggerInfo();
        triggerInfo.setRepeatCount(trigger.getRepeatCount());
        triggerInfo.setRepeatIntervall(trigger.getRepeatInterval());
        triggerInfo.setStartTime(trigger.getStartTime());
        triggerInfo.setEndTime(trigger.getEndTime());
        return triggerInfo;
    }
    
    private CronTriggerInfo createCronTriggerInfo(CronTrigger trigger) {
        CronTriggerInfo triggerInfo = new CronTriggerInfo();
        String[] parts = trigger.getCronExpression().split(" ");
        triggerInfo.setSecond(parts[0]);
        triggerInfo.setMinute(parts[1]);
        triggerInfo.setHour(parts[2]);
        triggerInfo.setDayOfMonth(parts[3]);
        triggerInfo.setMonth(parts[4]);
        triggerInfo.setDayOfWeek(parts[5]);
        triggerInfo.setYear(parts[6]);
        return triggerInfo;
    }
    
    private Trigger getJobTrigger(JobKey jobKey) {
        List<? extends Trigger> triggers;
        try {
            triggers = this.scheduler.getTriggersOfJob(jobKey);
        } catch (SchedulerException e) {
            throw new InternalSchedulerException(e);
        }
        if (triggers.size() == 0) {
            logger.warn("Job '" + jobKey + "' has no trigger");
            return null;
        } else if (triggers.size() > 1) {
            logger.warn("Job '" + jobKey + "' has more than one trigger! Returning just the first");
        }
        return triggers.get(0);
    }
    
    private String getActionFromJobClass(Class<?> jobClass) {
        return jobClasses.entrySet().stream().filter(entry -> entry.getValue().equals(jobClass))
                .findFirst().get().getKey();
    }
}
