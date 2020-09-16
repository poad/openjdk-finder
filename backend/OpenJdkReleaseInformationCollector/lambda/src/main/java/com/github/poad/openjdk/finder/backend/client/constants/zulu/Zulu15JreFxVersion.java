package com.github.poad.openjdk.finder.backend.client.constants.zulu;

import com.github.poad.openjdk.finder.backend.entity.JavaVersion;

import java.util.stream.Stream;

public class Zulu15JreFxVersion extends ZuluJavaVersion {
    public Zulu15JreFxVersion(String arch, String installationType, String extension, String os, String checksumType, String checksum) {
        super("15.27.17", 15, arch, "15.0.0", installationType, extension, os, "jre", true, checksumType, checksum);
    }

    public Zulu15JreFxVersion(String zuluVersion, String arch, String version, String installationType, String extension, String os, String checksumType, String checksum) {
        super(zuluVersion, 15, arch, version, installationType, extension, os, "jre", true, checksumType, checksum);
    }

    static final Stream<JavaVersion> JDK14 = Stream.of(
            new Zulu15JreFxVersion(
                    "x64",
                    "archive",
                    "tar.gz",
                    "linux",
                    "sha256",
                    "868743e4dbea8f0916044f86bc49ec379ef94f6c055fb3eed08645a8170ce829"
            ),
            new Zulu15JreFxVersion(
                    "x64",
                    "archive",
                    "zip",
                    "windows",
                    "sha256",
                    "ee3ac923ffe1cf632639b60cb25d140cb6e84084c50a37df4df9754ab62af0c9"
            ),
            new Zulu15JreFxVersion(
                    "x64",
                    "archive",
                    "tar.gz",
                    "macos",
                    "sha256",
                    "386e0666c1485033668f920ffe740bb47b46781f08988741b482293082f84ad3"
            )
    );
}
