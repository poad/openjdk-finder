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

    public List<JavaVersion> list(Integer majorVersion, String arch, String vendor, String type) throws ExecutionException, InterruptedException {
        if (Objects.isNull(majorVersion) && Objects.isNull(arch) && Objects.isNull(vendor) && Objects.isNull(type)) {
            return repository.list().get();
        } else if(Objects.nonNull(majorVersion)) {
            if (Objects.isNull(arch) && Objects.isNull(vendor)) {
                return repository.findByMajorVersion(majorVersion).get();
            } else if (Objects.isNull(arch)) {
                return repository.findByVendor(vendor).get();
            } else if (Objects.isNull(vendor)) {
                return repository.findByMajorVersionAndArch(majorVersion, arch).get();
            } else {
                if (Objects.isNull(type)) {
                    return repository.findByMajorVersionAndArchAndVendor(majorVersion, arch, vendor).get();
                }
                return repository.findByMajorVersionAndArchAndVendorAndType(majorVersion, arch, vendor, type).get();
            }
        } else if(Objects.nonNull(arch) && Objects.isNull(vendor) && Objects.isNull(type)) {
            return repository.findByArch(arch).get();
        } else if(Objects.nonNull(vendor) && Objects.isNull(type)) {
            return repository.findByVendor(vendor).get();
        } else {
            return repository.findByType(type).get();
        }
    }
}
