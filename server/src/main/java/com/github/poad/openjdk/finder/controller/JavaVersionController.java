package com.github.poad.openjdk.finder.controller;

import com.github.poad.openjdk.finder.service.JavaVersionService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
public class JavaVersionController {
    private final JavaVersionService service;

    public JavaVersionController(JavaVersionService service) {
        this.service = service;
    }

    @SuppressWarnings("unused")
    private static class JavaVersionItem {
        private final String id;

        private final String vendor;

        private final String distribution;

        private final int majorVersion;

        private final String arch;

        private final String version;

        private final String installationType;

        private final String extension;

        private final String url;

        private final String type;

        private final String bundle;

        private final boolean fx;

        private final String os;

        private final String checksumType;

        private final String checksum;

        private final String sigUrl;

        private final String timestamp;

        // for Jackson
        public JavaVersionItem() {
            this(null, null, null, -1, null, null, null, null, null, null, null, false, null, null, null, null, null);
        }

        JavaVersionItem(String id, String vendor, String distribution, int majorVersion, String arch, String version, String installationType, String extension, String url, String type, String bundle, boolean fx, String os, String checksumType, String checksum, String sigUrl, String timestamp) {
            this.id = id;
            this.vendor = vendor;
            this.distribution = distribution;
            this.majorVersion = majorVersion;
            this.arch = arch;
            this.version = version;
            this.installationType = installationType;
            this.extension = extension;
            this.url = url;
            this.type = type;
            this.bundle = bundle;
            this.fx = fx;
            this.os = os;
            this.checksumType = checksumType;
            this.checksum = checksum;
            this.sigUrl = sigUrl;
            this.timestamp = timestamp;
        }

        public String getId() {
            return id;
        }

        public String getVendor() {
            return vendor;
        }

        public String getDistribution() {
            return distribution;
        }

        public int getMajorVersion() {
            return majorVersion;
        }

        public String getArch() {
            return arch;
        }

        public String getVersion() {
            return version;
        }

        public String getType() {
            return type;
        }

        public String getBundle() {
            return bundle;
        }

        public boolean isFx() {
            return fx;
        }

        public String getOs() {
            return os;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public String getInstallationType() {
            return installationType;
        }

        public String getUrl() {
            return url;
        }

        public String getExtension() {
            return extension;
        }

        public String getChecksumType() {
            return checksumType;
        }

        public String getChecksum() {
            return this.checksum;
        }

        public String getSigUrl() {
            return this.sigUrl;
        }
}

    @GetMapping(path = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<JavaVersionItem> list(
            @RequestParam(required = false) Integer majorVersion,
            @RequestParam(required = false) String arch,
            @RequestParam(required = false) String vendor,
            @RequestParam(required = false) String distribution,
            @RequestParam(required = false) String installationType,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String bundle,
            @RequestParam(required = false) Boolean fx,
            @RequestParam(required = false) String os) throws ExecutionException, InterruptedException {
        return service.list(majorVersion, arch, vendor, distribution, installationType, type, bundle, os, fx)
                .stream()
                .map(item -> new JavaVersionItem(
                        item.getId(),
                        item.getVendor(),
                        item.getDistribution(),
                        item.getMajorVersion(),
                        item.getArch(),
                        item.getVersion(),
                        item.getInstallationType(),
                        item.getExtension(),
                        item.getUrl(),
                        item.getType(),
                        item.getBundle(),
                        item.getFx(),
                        item.getOs(),
                        item.getChecksumType(),
                        item.getChecksum(),
                        item.getSigUrl(),
                        item.getTimestamp()
                ))
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/vendors", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Set<String> vendors() throws ExecutionException, InterruptedException {
        return new HashSet<>(service.vendors());
    }

    @GetMapping(path = "/versions", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Set<Integer> versions() throws ExecutionException, InterruptedException {
        return new HashSet<>(service.versions());
    }

    @GetMapping(path = "/architectures", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Set<String> architectures() throws ExecutionException, InterruptedException {
        return new HashSet<>(service.architectures());
    }

    @GetMapping(path = "/distributions", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Set<String> distributions() throws ExecutionException, InterruptedException {
        return new HashSet<>(service.distributions());
    }

    @GetMapping(path = "/os", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Set<String> os() throws ExecutionException, InterruptedException {
        return new HashSet<>(service.os());
    }

    @GetMapping(path = "/types", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Set<String> types() throws ExecutionException, InterruptedException {
        return new HashSet<>(service.types());
    }

    @GetMapping(path = "/bundles", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Set<String> bundles() throws ExecutionException, InterruptedException {
        return new HashSet<>(service.bundles());
    }
}
