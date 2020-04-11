package com.github.poad.openjdk.finder.repository;

import com.github.poad.openjdk.finder.entity.JavaVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface JavaVersionRepository extends JpaRepository<JavaVersion, String>, JpaSpecificationExecutor<JavaVersion> {
    @Async
    @Query("SELECT DISTINCT v.vendor FROM JavaVersion v ORDER BY v.vendor ASC")
    CompletableFuture<List<String>> listVendors();

    @Async
    @Query("SELECT DISTINCT v.majorVersion FROM JavaVersion v ORDER BY v.majorVersion ASC")
    CompletableFuture<List<Integer>> listVersions();

    @Async
    @Query("SELECT DISTINCT v.arch FROM JavaVersion v ORDER BY v.arch ASC")
    CompletableFuture<List<String>> listArchitectures();

    @Async
    @Query("SELECT DISTINCT v.distribution FROM JavaVersion v ORDER BY v.distribution ASC")
    CompletableFuture<List<String>> listDistributions();

    @Async
    @Query("SELECT DISTINCT v.os FROM JavaVersion v ORDER BY v.os ASC")
    CompletableFuture<List<String>> listOs();

    @Async
    @Query("SELECT DISTINCT v.type FROM JavaVersion v ORDER BY v.type ASC")
    CompletableFuture<List<String>> listTypes();

    @Async
    @Query("SELECT DISTINCT v.bundle FROM JavaVersion v ORDER BY v.bundle ASC")
    CompletableFuture<List<String>> listBundles();
}
