package com.github.poad.openjdk.finder.controller;

import com.github.poad.openjdk.finder.service.JavaVersionService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
public class JavaVersionController {
    private final JavaVersionService service;

    public JavaVersionController(JavaVersionService service) {
        this.service = service;
    }

    private static class JavaVersionItem {
        private final String id;

        private final String vendor;

        private final int majorVersion;

        private final String arch;

        private final String version;

        private final String url;

        private final String type;

        private final String impl;

        private final String os;

        private final String timestamp;

        // for Jackson
        public JavaVersionItem() {
            this(null, null, -1, null, null, null, null, null, null, null);
        }

        JavaVersionItem(String id, String vendor, int majorVersion, String arch, String version, String url, String type, String impl, String os, String timestamp) {
            this.id = id;
            this.vendor = vendor;
            this.majorVersion = majorVersion;
            this.arch = arch;
            this.version = version;
            this.url = url;
            this.type = type;
            this.impl = impl;
            this.os = os;
            this.timestamp = timestamp;
        }

        public String getId() {
            return id;
        }

        public String getVendor() {
            return vendor;
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

        public String getUrl() {
            return url;
        }

        public String getType() {
            return type;
        }

        public String getImpl() {
            return impl;
        }

        public String getOs() {
            return os;
        }

        public String getTimestamp() {
            return timestamp;
        }
    }

    @GetMapping(path = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<JavaVersionItem> list(
            @RequestParam(required = false) Integer majorVersion,
            @RequestParam(required = false) String arch,
            @RequestParam(required = false) String vendor,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String impl,
            @RequestParam(required = false) String os) throws ExecutionException, InterruptedException {
        return service.list(majorVersion, arch, vendor, type, impl, os)
                .stream()
                .map(item -> new JavaVersionItem(
                        item.getId(), item.getVendor(), item.getMajorVersion(), item.getArch(), item.getVersion(), item.getUrl(), item.getType(), item.getImpl(), item.getOs(), item.getTimestamp()
                ))
                .collect(Collectors.toList());
    }
}
