package com.klm.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@EnableEncryptableProperties
@SpringBootApplication
public class AirfranceOauthClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirfranceOauthClientApplication.class, args);
	}
}
