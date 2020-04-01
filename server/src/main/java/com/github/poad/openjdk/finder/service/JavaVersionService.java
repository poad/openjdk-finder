package com.github.poad.openjdk.finder.service;

import com.github.poad.openjdk.finder.entity.JavaVersion;
import com.github.poad.openjdk.finder.repository.JavaVersionRepository;
import com.github.poad.openjdk.finder.repository.JavaVersionSpecs;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
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
        Stream.of(
                JavaVersionSpecs.arch(arch),
                JavaVersionSpecs.vendor(vendor),
                JavaVersionSpecs.type(type),
                JavaVersionSpecs.impl(impl),
                JavaVersionSpecs.os(os))
                .forEach(spec::and);
        return repository.findAll(spec);
    }
}
