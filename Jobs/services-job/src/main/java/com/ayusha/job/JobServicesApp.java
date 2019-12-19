package com.ayusha.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * This is the service main application for job management app. This controls
 * the access to the services
 *
 * @author Finch
 * @version 1.0
 * @since 01-Aug-2019
 */
@SpringBootApplication
@EnableEurekaClient
public class JobServicesApp {

	/**
	 * main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(JobServicesApp.class, args);
	}
}