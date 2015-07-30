package demo.web;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestConfig extends ResourceConfig {
	    public RestConfig() {
	        register(ContactRestController.class);
	    }
	}
