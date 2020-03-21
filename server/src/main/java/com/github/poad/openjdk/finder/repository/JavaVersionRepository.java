package com.github.poad.openjdk.finder.repository;

import com.github.poad.openjdk.finder.entity.JavaVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface JavaVersionRepository extends JpaRepository<JavaVersion, String>, JpaSpecificationExecutor<JavaVersion> {
    @Async
    @Query("select v from JavaVersion v")
    CompletableFuture<List<JavaVersion>> list();

    @Async
    @Query("select v from JavaVersion v where v.majorVersion = :majorVersion")
    CompletableFuture<List<JavaVersion>> findByMajorVersion(@Param("majorVersion") int majorVersion);

    @Async
    @Query("select v from JavaVersion v where v.vendor = :vendor")
    CompletableFuture<List<JavaVersion>> findByVendor(@Param("vendor") String vendor);

    @Async
    @Query("select v from JavaVersion v where v.arch = :arch")
    CompletableFuture<List<JavaVersion>> findByArch(@Param("arch") String arch);

    @Async
    @Query("select v from JavaVersion v where v.majorVersion = :majorVersion and v.vendor = :vendor")
    CompletableFuture<List<JavaVersion>> findByMajorVersionAndVendor(@Param("majorVersion") int majorVersion, @Param("vendor") String vendor);

    @Async
    @Query("select v from JavaVersion v where v.majorVersion = :majorVersion and v.arch = :arch")
    CompletableFuture<List<JavaVersion>> findByMajorVersionAndArch(@Param("majorVersion") int majorVersion, @Param("arch") String arch);

    @Async
    @Query("select v from JavaVersion v where v.majorVersion = :majorVersion and v.arch = :arch and v.vendor = :vendor")
    CompletableFuture<List<JavaVersion>> findByMajorVersionAndArchAndVendor(@Param("majorVersion") int majorVersion, @Param("arch") String arch, @Param("vendor") String vendor);
}
