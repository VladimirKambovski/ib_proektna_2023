package com.example.ib_proektna_2023;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class IbProektna2023Application {

	public static void main(String[] args) {
		SpringApplication.run(IbProektna2023Application.class, args);
	}

}
