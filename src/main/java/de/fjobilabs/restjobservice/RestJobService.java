package de.fjobilabs.restjobservice;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author Felix Jordan
 * @since 09.08.2016 - 17:20:00
 * @version 1.0
 */
@Configuration
@ComponentScan
@ImportResource("rest-job-service-context.xml")
public class RestJobService {
}
