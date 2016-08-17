package de.fjobilabs.restjobservice.callbackserver.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author Felix Jordan
 * @since 16.08.2016 - 20:47:08
 * @version 1.0
 */
@Document(collection="callbacks")
public class CallbackInfo {
    
    public static final String WAITING = "waiting";
    public static final String CALLED = "called";
    public static final String ERROR = "error";
    
    @Id
    private String id;
    private String state;
    private String callbackClass;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String status) {
        this.state = status;
    }
    
    @Field("callback-class")
    public String getCallbackClass() {
        return callbackClass;
    }
    
    @Field("callback-class")
    public void setCallbackClass(String callbackClass) {
        this.callbackClass = callbackClass;
    }
}
