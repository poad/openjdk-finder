package com.github.poad.openjdk.finder.backend.service;

import com.github.poad.openjdk.finder.backend.repository.JavaVersionRepository;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.util.Optional;

public class JavaVersionService {
    private final Jdbi jdbi;

    public JavaVersionService() {
        String url = Optional.ofNullable(System.getenv("JDBC_URL")).orElse("jdbc:postgresql://localhost:5432/test");
        String user = Optional.ofNullable(System.getenv("JDBC_USER")).orElse("postgres");
        String password = Optional.ofNullable(System.getenv("JDBC_PASSWORD")).orElse("root");

        jdbi = Jdbi.create(url, user, password);
        jdbi.installPlugin(new SqlObjectPlugin());
    }

    public void registerJavaVersion(String id, String vendor, int majorVersion, String arch, String version, String url, String type, String impl, String os, String timestamp) {
        jdbi.withExtension(JavaVersionRepository.class, repository -> {
            if (repository.countById(id) > 0L) {
                repository.update(id, vendor, majorVersion, arch, version, url, type, impl, os, timestamp);
            } else {
                repository.insert(id, vendor, majorVersion, arch, version, url, type, impl, os, timestamp);
            }
            return null;
        });
    }
}
