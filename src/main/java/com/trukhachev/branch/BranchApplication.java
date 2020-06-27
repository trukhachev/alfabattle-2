package com.trukhachev.branch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class BranchApplication {

	public static void main(String[] args) {
		SpringApplication.run(BranchApplication.class);
	}

}
