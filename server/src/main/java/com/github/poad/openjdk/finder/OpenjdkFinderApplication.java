package com.github.poad.openjdk.finder;

import com.github.poad.openjdk.finder.config.Cors;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Set;

@EnableAsync
@SpringBootApplication
@EnableConfigurationProperties(Cors.class)
public class OpenjdkFinderApplication {

	private final Cors cors;
	private final Set<String> paths = Set.of(
			"/",
			"/vendors",
			"/versions",
			"/architectures",
			"/distributions",
			"/os",
			"/types",
			"/bundles"
	);

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
					paths.forEach(
							path -> registry.addMapping(path).allowedOrigins(origin)
					);
				}));
			}
		};
	}
}
