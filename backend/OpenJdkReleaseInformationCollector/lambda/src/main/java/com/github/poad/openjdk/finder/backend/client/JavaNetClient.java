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
                        "java.net-15-jdk-hotspot-x64-tar.gz-linux",
                        new JavaVersion(
                                "java.net-15-jdk-hotspot-x64-tar.gz-linux",
                                "java.net",
                                "openjdk",
                                15,
                                "x64",
                                "15",
                                "archive",
                                null,
                                "https://download.java.net/java/GA/jdk15/779bf45e88a44cbd9ea6621d33e33db1/36/GPL/openjdk-15_linux-x64_bin.tar.gz",
                                "hotspot",
                                "linux",
                                "jdk",
                                false,
                                "sha256",
                                "https://download.java.net/java/GA/jdk15/779bf45e88a44cbd9ea6621d33e33db1/36/GPL/openjdk-15_linux-x64_bin.tar.gz.sha256",
                                null,
                                null)
                ),
                Map.entry(
                        "java.net-15-jdk-hotspot-x64-zip-windows",
                        new JavaVersion(
                                "java.net-15-jdk-hotspot-x64-zip-windows",
                                "java.net",
                                "openjdk",
                                15,
                                "x64",
                                "15",
                                "archive",
                                null,
                                "https://download.java.net/java/GA/jdk15/779bf45e88a44cbd9ea6621d33e33db1/36/GPL/openjdk-15_windows-x64_bin.zip",
                                "hotspot",
                                "windows",
                                "jdk",
                                false,
                                "sha256",
                                "https://download.java.net/java/GA/jdk15/779bf45e88a44cbd9ea6621d33e33db1/36/GPL/openjdk-15_windows-x64_bin.zip.sha256",
                                null,
                                null)
                ),
                Map.entry(
                        "java.net-15-jdk-hotspot-x64-tar.gz-macos",
                        new JavaVersion(
                                "java.net-15-jdk-hotspot-x64-tar.gz-macos",
                                "java.net",
                                "openjdk",
                                15,
                                "x64",
                                "15",
                                "archive",
                                null,
                                "https://download.java.net/java/GA/jdk15/779bf45e88a44cbd9ea6621d33e33db1/36/GPL/openjdk-15_osx-x64_bin.tar.gz",
                                "hotspot",
                                "macos",
                                "jdk",
                                false,
                                "sha256",
                                "https://download.java.net/java/GA/jdk15/779bf45e88a44cbd9ea6621d33e33db1/36/GPL/openjdk-15_osx-x64_bin.tar.gz.sha256",
                                null,
                                null)
                ),
                Map.entry(
                        "java.net-15-jdk-hotspot-aarch64-tar.gz-macos",
                        new JavaVersion(
                                "java.net-15-jdk-hotspot-aarch64-tar.gz-macos",
                                "java.net",
                                "openjdk",
                                15,
                                "aarch64",
                                "15",
                                "archive",
                                null,
                                "https://download.java.net/java/GA/jdk15/779bf45e88a44cbd9ea6621d33e33db1/36/GPL/openjdk-15_linux-aarch64_bin.tar.gz",
                                "hotspot",
                                "linux",
                                "jdk",
                                false,
                                "sha256",
                                "https://download.java.net/java/GA/jdk15/779bf45e88a44cbd9ea6621d33e33db1/36/GPL/openjdk-15_linux-aarch64_bin.tar.gz.sha256",
                                null,
                                null)
                )
        ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
