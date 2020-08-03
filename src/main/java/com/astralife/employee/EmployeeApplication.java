package com.astralife.employee;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableJpaRepositories

/*@EnableConfigurationProperties({
		FileStorageProperties.class
})*/

// @EnableAutoConfiguration(exclude = WebMvcAutoConfiguration.class)
@EntityScan(basePackages = {"com.astralife.employee"})
@ComponentScan(basePackages = {"com.astralife.employee"})
public class EmployeeApplication extends SpringBootServletInitializer implements ApplicationListener<ContextClosedEvent>
{
	private static final Logger log = LoggerFactory.getLogger(EmployeeApplication.class);

	public static void main(String... args) {
		try {
			SpringApplication.run(EmployeeApplication.class, args);
		} catch (Exception e) {
			log.debug("EXCEPTION : MAIN APPLICATION");
			log.error(e.getMessage());
		}
	}

	@PostConstruct
	public void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Jakarta"));
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(EmployeeApplication.class);
	}

	@Override
	public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
		log.info(String.format("================> Stop %s <================", EmployeeApplication.class.getSimpleName()));
	}
}
