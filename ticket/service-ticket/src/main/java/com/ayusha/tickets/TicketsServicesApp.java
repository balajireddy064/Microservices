package com.ayusha.tickets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.client.RestTemplate;

/**
 * This is the service main application for ticket management app. This controls
 * the access to the services
 *
 * @author Finch
 * @version 1.0
 * @since 01-Aug-2019
 */

@SpringBootApplication
@EnableDiscoveryClient
public class TicketsServicesApp {

	/**
	 * main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(TicketsServicesApp.class, args);
	}

	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}