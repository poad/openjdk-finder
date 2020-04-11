package com.github.poad.openjdk.finder;

import com.github.poad.openjdk.finder.config.Cors;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableAsync
@SpringBootApplication
@EnableConfigurationProperties(Cors.class)
public class OpenjdkFinderApplication {

	private final Cors cors;

	OpenjdkFinderApplication(Cors cors) {
		this.cors = cors;
	}

	public static void main(String... args) {
		SpringApplication.run(OpenjdkFinderApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		var cors = this.cors;
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				cors.getOrigins().ifPresent(origins -> origins.forEach(origin -> {
					registry.addMapping("/").allowedOrigins(origin);
					registry.addMapping("/vendors").allowedOrigins(origin);
					registry.addMapping("/versions").allowedOrigins(origin);
					registry.addMapping("/architectures").allowedOrigins(origin);
					registry.addMapping("/distributions").allowedOrigins(origin);
					registry.addMapping("/os").allowedOrigins(origin);
					registry.addMapping("/types").allowedOrigins(origin);
					registry.addMapping("/bundles").allowedOrigins(origin);
				}));
			}
		};
	}
}
