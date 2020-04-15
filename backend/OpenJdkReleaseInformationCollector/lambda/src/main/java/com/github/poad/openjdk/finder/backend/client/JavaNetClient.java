package com.github.poad.openjdk.finder.backend.client;

import com.github.poad.openjdk.finder.backend.entity.JavaVersion;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaNetClient extends JsonHttpClient implements OpenJdkApiClient {
    @Override
    public Map<String, JavaVersion> getVersions() {
        return Stream.of(
                Map.entry(
                        "java.net-14-jdk-hotspot-x64-tar.gz-linux",
                        new JavaVersion(
                                "java.net-14-jdk-hotspot-x64-tar.gz-linux",
                                "java.net",
                                "openjdk",
                                14,
                                "x64",
                                "14.0.1",
                                "archive",
                                null,
                                "https://download.java.net/java/GA/jdk14.0.1/664493ef4a6946b186ff29eb326336a2/7/GPL/openjdk-14.0.1_linux-x64_bin.tar.gz",
                                "hotspot",
                                "linux",
                                "jdk",
                                false,
                                null)
                ),
                Map.entry(
                        "java.net-14-jdk-hotspot-x64-zip-windows",
                        new JavaVersion(
                                "java.net-14-jdk-hotspot-x64-zip-windows",
                                "java.net",
                                "openjdk",
                                14,
                                "x64",
                                "14.0.1",
                                "archive",
                                null,
                                "https://download.java.net/java/GA/jdk14.0.1/664493ef4a6946b186ff29eb326336a2/7/GPL/openjdk-14.0.1_windows-x64_bin.zip",
                                "hotspot",
                                "windows",
                                "jdk",
                                false,
                                null)
                ),
                Map.entry(
                        "java.net-14-jdk-hotspot-x64-tar.gz-macos",
                        new JavaVersion(
                                "java.net-14-jdk-hotspot-x64-tar.gz-macos",
                                "java.net",
                                "openjdk",
                                14,
                                "x64",
                                "14.0.1",
                                "archive",
                                null,
                                "https://download.java.net/java/GA/jdk14.0.1/664493ef4a6946b186ff29eb326336a2/7/GPL/openjdk-14.0.1_osx-x64_bin.tar.gz",
                                "hotspot",
                                "macos",
                                "jdk",
                                false,
                                null)
                )
        ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
