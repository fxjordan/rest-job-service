package de.fjobilabs.restjobservice.callbackserver.callback;

import de.fjobilabs.restjobservice.callbackserver.domain.JobCallbackData;

/**
 * @author Felix Jordan
 * @since 16.08.2016 - 20:54:05
 * @version 1.0
 */
public interface JobCallback {
    
    public void execute(JobCallbackData jobCallbackData);
}
