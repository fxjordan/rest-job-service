package de.fjobilabs.restjobservice.quartz;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.fjobilabs.restjobservice.domain.JobCallbackData;

/**
 * @author Felix Jordan
 * @since 16.08.2016 - 15:08:46
 * @version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JobCallback {
    
    String status() default JobCallbackData.ERROR;
    
    String message() default "";
}
