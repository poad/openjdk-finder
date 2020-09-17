package com.github.poad.openjdk.finder.backend.client;

import com.github.poad.openjdk.finder.backend.client.constants.java.net.JavaNetJdk15Version;
import com.github.poad.openjdk.finder.backend.entity.JavaVersion;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaNetClient extends JsonHttpClient implements OpenJdkApiClient {
    @Override
    public Map<String, JavaVersion> getVersions() {
        return Stream.of(
                new JavaNetJdk15Version(
                        JavaVersion.Arch.AMD64.getArch(),
                        "archive",
                        "tar.gz",
                        JavaVersion.OS.LINUX.getOs()
                ),
                new JavaNetJdk15Version(
                        JavaVersion.Arch.AMD64.getArch(),
                        "archive",
                        "zip",
                        JavaVersion.OS.WINDOWS.getOs()
                ),
                new JavaNetJdk15Version(
                        JavaVersion.Arch.AMD64.getArch(),
                        "archive",
                        "zip",
                        JavaVersion.OS.MACOS.getOs()
                ),
                new JavaNetJdk15Version(
                        JavaVersion.Arch.ARM64.getArch(),
                        "archive",
                        "tar.gz",
                        JavaVersion.OS.LINUX.getOs()
                )
        ).map(v -> Map.entry(v.getId(), v)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
