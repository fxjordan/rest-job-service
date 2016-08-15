package de.fjobilabs.restjobservice;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Felix Jordan
 * @since 09.08.2016 - 17:20:00
 * @version 1.0
 */
@Configuration
@ComponentScan
@ImportResource("rest-job-service-server-context.xml")
@PropertySource("rest-job-service-server.properties")
public class RestJobServiceServer {
}
