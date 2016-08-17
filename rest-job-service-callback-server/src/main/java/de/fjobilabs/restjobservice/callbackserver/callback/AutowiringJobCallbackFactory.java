package de.fjobilabs.restjobservice.callbackserver.callback;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import de.fjobilabs.restjobservice.callbackserver.domain.CallbackInfo;

/**
 * @author Felix Jordan
 * @since 17.08.2016 - 18:31:51
 * @version 1.0
 */
public class AutowiringJobCallbackFactory extends SimpleJobCallbackFactory
        implements ApplicationContextAware {
    
    private transient AutowireCapableBeanFactory beanFactory;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.beanFactory = applicationContext.getAutowireCapableBeanFactory();
    }
    
    @Override
    protected Object createCallbackInstance(CallbackInfo callbackInfo) throws Exception {
        Object callback =  super.createCallbackInstance(callbackInfo);
        this.beanFactory.autowireBean(callback);
        return callback;
    }
}
