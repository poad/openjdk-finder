package com.github.poad.openjdk.finder.backend.client;

import com.github.poad.openjdk.finder.backend.entity.JavaVersion;

import java.util.Map;

public interface OpenJdkApiClient {
    Map<String, JavaVersion> getVersions();
}

enum InstallerType {
    INSTALLER("installer"),
    ARCHIVE("archive");

    private final String installerType;

    InstallerType(String installerType) {
        this.installerType = installerType;
    }

    @Override
    public String toString() {
        return this.installerType;
    }

}
