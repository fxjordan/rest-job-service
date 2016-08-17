package de.fjobilabs.restjobservice.callbackserver.callback;

import de.fjobilabs.restjobservice.callbackserver.domain.CallbackInfo;

/**
 * @author Felix Jordan
 * @since 17.08.2016 - 18:20:04
 * @version 1.0
 */
public interface JobCallbackFactory {
    
    public JobCallback createJobCallback(CallbackInfo callbackInfo);
}
