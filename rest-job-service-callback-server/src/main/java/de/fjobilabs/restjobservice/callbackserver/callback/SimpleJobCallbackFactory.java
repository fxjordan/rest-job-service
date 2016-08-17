package de.fjobilabs.restjobservice.callbackserver.callback;

import de.fjobilabs.restjobservice.callbackserver.domain.CallbackInfo;
import de.fjobilabs.restjobservice.callbackserver.exceptions.CallbackInstantiationException;

/**
 * @author Felix Jordan
 * @since 17.08.2016 - 18:24:03
 * @version 1.0
 */
public class SimpleJobCallbackFactory implements JobCallbackFactory {
    
    @Override
    public JobCallback createJobCallback(CallbackInfo callbackInfo) {
        try {
            Object callback = createCallbackInstance(callbackInfo);
            if (callback instanceof JobCallback) {
                return (JobCallback) callback;
            }
            throw new IllegalArgumentException("Unable to execute callback class ["
                    + callback.getClass().getName()
                    + "]: only [de.fjobilabs.restjobservice.callbackserver.callback.JobCallback] supported.");
        } catch (Exception e) {
            throw new CallbackInstantiationException("Callback instantiation failed", e);
        }
    }
    
    protected Object createCallbackInstance(CallbackInfo callbackInfo) throws Exception {
        return Class.forName(callbackInfo.getCallbackClass()).newInstance();
    }
}
