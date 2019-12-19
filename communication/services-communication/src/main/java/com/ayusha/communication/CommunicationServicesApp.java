package com.ayusha.communication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * This is the service main application for Communication management app. This
 * controls the access to the services
 *
 * @author Author
 * @version 1.0
 * @since 01-Aug-2019
 */
@SpringBootApplication
@EnableEurekaClient
public class CommunicationServicesApp {

	/**
	 * main method
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		SpringApplication.run(CommunicationServicesApp.class, args);
	}

	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}