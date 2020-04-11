package com.github.poad.openjdk.finder.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.List;
import java.util.Optional;

@ConstructorBinding
@ConfigurationProperties(prefix = "app.cors")
public class Cors {
    private final List<String> origins;

    public Cors(List<String> origins) {
        this.origins = origins;
    }

    public Optional<List<String>> getOrigins() {
        return Optional.ofNullable(origins);
    }
}
