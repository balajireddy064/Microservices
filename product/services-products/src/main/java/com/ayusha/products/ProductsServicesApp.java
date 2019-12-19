package com.ayusha.products;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
* This is the service main application for ticket management app.
* This controls the access to the services
*
* @author  Author1
* @version 1.0
* @since   2019-05-03 
*/
@EnableDiscoveryClient
@SpringBootApplication
public class ProductsServicesApp {  
	
	/**
	 * main method
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ProductsServicesApp.class, args);
    }   
	
	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
}            