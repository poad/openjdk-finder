package com.github.poad.openjdk.finder.backend.client;

import com.github.poad.openjdk.finder.backend.entity.JavaVersion;

import java.util.Map;

public interface OpenJdkApiClient {
    Map<String, JavaVersion> getVersions();
}
