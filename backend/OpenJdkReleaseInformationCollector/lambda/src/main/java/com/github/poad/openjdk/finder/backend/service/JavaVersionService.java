package com.github.poad.openjdk.finder.backend.service;

import com.github.poad.openjdk.finder.backend.entity.JavaVersion;
import com.github.poad.openjdk.finder.backend.repository.JavaVersionRepository;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.util.List;
import java.util.Optional;

public class JavaVersionService {
    private final Jdbi jdbi;
    private final String distribution;

    public JavaVersionService(String distribution) {
        String url = Optional.ofNullable(System.getenv("JDBC_URL")).orElse("jdbc:postgresql://localhost:5432/test");
        String user = Optional.ofNullable(System.getenv("JDBC_USER")).orElse("postgres");
        String password = Optional.ofNullable(System.getenv("JDBC_PASSWORD")).orElse("root");

        this.distribution = distribution;
        jdbi = Jdbi.create(url, user, password);
        jdbi.installPlugin(new SqlObjectPlugin());
    }

    public void registerJavaVersion(JavaVersion version) {
        jdbi.withExtension(JavaVersionRepository.class, repository -> {
            if (repository.countById(version.getId()) > 0L) {
                repository.update(version);
            } else {
                repository.insert(version);
            }
            return null;
        });
    }

    public void delete(List<String> ids) {
        jdbi.withExtension(JavaVersionRepository.class, repository -> {
            repository.delete(distribution, ids);
            return null;
        });
    }
}
