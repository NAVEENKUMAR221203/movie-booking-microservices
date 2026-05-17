package com.example.server.pr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServerPrApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerPrApplication.class, args);
	}

}
