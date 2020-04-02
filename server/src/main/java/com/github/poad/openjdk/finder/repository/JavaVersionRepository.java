package com.github.poad.openjdk.finder.repository;

import com.github.poad.openjdk.finder.entity.JavaVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JavaVersionRepository extends JpaRepository<JavaVersion, String>, JpaSpecificationExecutor<JavaVersion> {
}
