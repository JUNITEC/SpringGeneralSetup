package com.example.DefaultAuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableAuthorizationServer
public class DefaultAuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DefaultAuthServerApplication.class, args);
	}

}
