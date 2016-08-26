package de.fjobilabs.restjobservice;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Felix Jordan
 * @since 26.08.2016 - 16:17:30
 * @version 1.0
 */
@Configuration
@ComponentScan
@ImportResource("rest-job-service-server-interface-context.xml")
@PropertySource("rest-job-service-server-interface.properties")
public class RestJobServiceServerInterface {
}
