package com.ayusha.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * This is the service main application for user management app. This controls
 * the access to the services
 *
 * @author Finch
 * @version 1.0
 * @since 01-Aug-2019 User Service Methods
 */
@EnableEurekaClient
@SpringBootApplication
public class UserServicesApp {

	/**
	 * main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(UserServicesApp.class, args);
		System.out.println(
				"===================spring boot============== user service ===================started===================");
	}
}