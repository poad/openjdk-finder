package com.github.poad.openjdk.finder.service;

import com.github.poad.openjdk.finder.entity.JavaVersion;
import com.github.poad.openjdk.finder.repository.JavaVersionRepository;
import com.github.poad.openjdk.finder.repository.JavaVersionSpecs;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

@Service
public class JavaVersionService {
    private final JavaVersionRepository repository;

    public JavaVersionService(JavaVersionRepository repository) {
        this.repository = repository;
    }

    public List<JavaVersion> list(Integer majorVersion, String arch, String vendor, String type, String impl, String os) throws ExecutionException, InterruptedException {
        var spec = Specification.where(JavaVersionSpecs.majorVersion(majorVersion));
        spec = Objects.nonNull(spec) ? spec.and(JavaVersionSpecs.arch(arch)) : Specification.where(JavaVersionSpecs.arch(arch));
        spec = Objects.nonNull(spec) ? spec.and(JavaVersionSpecs.vendor(vendor)) : Specification.where(JavaVersionSpecs.vendor(vendor));
        spec = Objects.nonNull(spec) ? spec.and(JavaVersionSpecs.type(type)) : Specification.where(JavaVersionSpecs.type(type));
        spec = Objects.nonNull(spec) ? spec.and(JavaVersionSpecs.impl(impl)) : Specification.where(JavaVersionSpecs.impl(impl));
        spec = Objects.nonNull(spec) ? spec.and(JavaVersionSpecs.os(os)) : Specification.where(JavaVersionSpecs.os(os));

        return repository.findAll(spec);
    }
}
