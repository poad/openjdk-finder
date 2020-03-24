package com.github.poad.openjdk.finder.service;

import com.github.poad.openjdk.finder.entity.JavaVersion;
import com.github.poad.openjdk.finder.repository.JavaVersionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Service
public class JavaVersionService {
    private final JavaVersionRepository repository;

    public JavaVersionService(JavaVersionRepository repository) {
        this.repository = repository;
    }

    public List<JavaVersion> list(Integer majorVersion, String arch, String vendor) throws ExecutionException, InterruptedException {
        if (Objects.isNull(majorVersion) && Objects.isNull(arch) && Objects.isNull(vendor)) {
            return repository.list().get();
        } else if(Objects.nonNull(majorVersion)) {
            if (Objects.isNull(arch) && Objects.isNull(vendor)) {
                return repository.findByMajorVersion(majorVersion).get();
            } else  if (Objects.isNull(arch)) {
                return repository.findByVendor(vendor).get();
            } else if (Objects.isNull(vendor)) {
                return repository.findByMajorVersionAndArch(majorVersion, arch).get();
            } else {
                return repository.findByMajorVersionAndArchAndVendor(majorVersion, arch, vendor).get();
            }
        } else if(Objects.nonNull(arch) && Objects.isNull(vendor)) {
            return repository.findByArch(arch).get();
        } else {
            return repository.findByVendor(vendor).get();
        }
    }
}
