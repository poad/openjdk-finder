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
                        "x64",
                        "archive",
                        "tar.gz",
                        "linux"
                ),
                new JavaNetJdk15Version(
                        "x64",
                        "archive",
                        "zip",
                        "windows"
                ),
                new JavaNetJdk15Version(
                        "x64",
                        "archive",
                        "zip",
                        "macos"
                ),
                new JavaNetJdk15Version(
                        "aarch64",
                        "archive",
                        "tar.gz",
                        "linux"
                )
        ).map(v -> Map.entry(v.getId(), v)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
