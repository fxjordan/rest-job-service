package de.fjobilabs.restjobservice.callbackserver;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author Felix Jordan
 * @since 16.08.2016 - 20:33:14
 * @version 1.0
 */
@Configuration
@ComponentScan
@EnableMongoRepositories
@ImportResource("rest-job-service-callback-server-context.xml")
@PropertySource("rest-job-service-callback-server.properties")
public class RestJobServiceCallbackServer {
}
