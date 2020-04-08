package com.github.poad.openjdk.finder.service;

import com.github.poad.openjdk.finder.entity.JavaVersion;
import com.github.poad.openjdk.finder.repository.JavaVersionRepository;
import com.github.poad.openjdk.finder.repository.JavaVersionSpecs;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@Service
public class JavaVersionService {
    private final JavaVersionRepository repository;

    public JavaVersionService(JavaVersionRepository repository) {
        this.repository = repository;
    }

    public List<JavaVersion> list(Integer majorVersion, String arch, String vendor, String distribution, String installationType, String type, String bundle, String os, Boolean fx) throws ExecutionException, InterruptedException {
        var spec = Specification.where(JavaVersionSpecs.majorVersion(majorVersion));
        spec = Objects.nonNull(spec) ? spec.and(JavaVersionSpecs.arch(arch)) : Specification.where(JavaVersionSpecs.arch(arch));
        spec = Objects.nonNull(spec) ? spec.and(JavaVersionSpecs.vendor(vendor)) : Specification.where(JavaVersionSpecs.vendor(vendor));
        spec = Objects.nonNull(spec) ? spec.and(JavaVersionSpecs.distribution(distribution)) : Specification.where(JavaVersionSpecs.distribution(distribution));
        spec = Objects.nonNull(spec) ? spec.and(JavaVersionSpecs.installationType(installationType)) : Specification.where(JavaVersionSpecs.installationType(installationType));
        spec = Objects.nonNull(spec) ? spec.and(JavaVersionSpecs.type(type)) : Specification.where(JavaVersionSpecs.type(type));
        spec = Objects.nonNull(spec) ? spec.and(JavaVersionSpecs.bundle(bundle)) : Specification.where(JavaVersionSpecs.bundle(bundle));
        spec = Objects.nonNull(spec) ? spec.and(JavaVersionSpecs.fx(fx)) : Specification.where(JavaVersionSpecs.fx(fx));
        spec = Objects.nonNull(spec) ? spec.and(JavaVersionSpecs.os(os)) : Specification.where(JavaVersionSpecs.os(os));

        return repository.findAll(spec);
    }

    public Set<String> vendors() throws ExecutionException, InterruptedException {
        return new HashSet<>(repository.listVendors().get());
    }

    public Set<Integer> versions() throws ExecutionException, InterruptedException {
        return new HashSet<>(repository.listVersions().get());
    }

    public Set<String> architectures() throws ExecutionException, InterruptedException {
        return new HashSet<>(repository.listArchitectures().get());
    }

    public Set<String> distributions() throws ExecutionException, InterruptedException {
        return new HashSet<>(repository.listDistributions().get());
    }

    public Set<String> os() throws ExecutionException, InterruptedException {
        return new HashSet<>(repository.listOs().get());
    }

    public Set<String> types() throws ExecutionException, InterruptedException {
        return new HashSet<>(repository.listTypes().get());
    }

    public Set<String> bundles() throws ExecutionException, InterruptedException {
        return new HashSet<>(repository.listBundles().get());
    }
}
