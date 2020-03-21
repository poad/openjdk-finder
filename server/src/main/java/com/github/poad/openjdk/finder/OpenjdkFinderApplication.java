package com.github.poad.openjdk.finder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class OpenjdkFinderApplication {

	public static void main(String... args) {
		SpringApplication.run(OpenjdkFinderApplication.class, args);
	}

}
