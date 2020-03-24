package com.github.poad.openjdk.finder.controller;

import com.github.poad.openjdk.finder.service.JavaVersionService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Valid
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

        // for Jackson
        public JavaVersionItem() {
            this(null, null, -1, null, null, null);
        }

        JavaVersionItem(String id, String vendor, int majorVersion, String arch, String version, String url) {
            this.id = id;
            this.vendor = vendor;
            this.majorVersion = majorVersion;
            this.arch = arch;
            this.version = version;
            this.url = url;
        }
    }

    @GetMapping
    public List<JavaVersionItem> list(
            @RequestParam("majorVersion") Integer majorVersion,
            @RequestParam("arch") String arch,
            @RequestParam("vendor") String vendor) throws ExecutionException, InterruptedException {
        return service.list(majorVersion, arch, vendor)
                .stream()
                .map(item -> new JavaVersionItem(
                        item.getId(), item.getVendor(), item.getMajorVersion(), item.getArch(), item.getVersion(), item.getUrl()
                ))
                .collect(Collectors.toList());
    }
}
