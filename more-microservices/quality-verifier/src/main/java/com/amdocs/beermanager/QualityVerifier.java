package com.amdocs.beermanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class QualityVerifier {

	public static void main(String[] args) {
		SpringApplication.run(QualityVerifier.class, args);
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}

@RestController
@Slf4j
class BeerGreeting {
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/verify-beer-quality")
	@HystrixCommand
	String verifyBeerQuality(){
		log.info("In method verify-beer-quality");
		return "ok";
	}
}
