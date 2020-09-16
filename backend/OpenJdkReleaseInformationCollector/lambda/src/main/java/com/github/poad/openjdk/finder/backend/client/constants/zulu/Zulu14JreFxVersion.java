package com.github.poad.openjdk.finder.backend.client.constants.zulu;

import com.github.poad.openjdk.finder.backend.entity.JavaVersion;

import java.util.stream.Stream;

public class Zulu14JreFxVersion extends ZuluJavaVersion {
    Zulu14JreFxVersion(String arch, String installationType, String extension, String os, String checksumType, String checksum) {
        super("14.29.23", 14, arch, "14.0.2", installationType, extension, os, "jdk", true, checksumType, checksum);
    }

    Zulu14JreFxVersion(String zuluVersion, String arch, String version, String installationType, String extension, String os, String checksumType, String checksum) {
        super(zuluVersion, 14, arch, version, installationType, extension, os, "jdk", true, checksumType, checksum);
    }

    public static final Stream<JavaVersion> VERSIONS = Stream.of(
            new Zulu14JreFxVersion(
                    "x64",
                    "archive",
                    "tar.gz",
                    "linux",
                    "sha256",
                    "deec89e38f5a9e1ed741ee00cd70e8c8a92b3c9e96aaa4b5349078f72d6dbefb"
            ),
            // windows
            new Zulu14JreFxVersion(
                    "x64",
                    "archive",
                    "zip",
                    "windows",
                    "sha256",
                    "3562a82e25e490c8069c6263dbaebddc3a10b831e24a8c41b5298c6c16330516"
            ),
            // macos
            new Zulu14JreFxVersion(
                    "x64",
                    "archive",
                    "tar.gz",
                    "macos",
                    "sha256",
                    "092285150ad7cc5a6c84423f32a827c7e39918edfd641cdeed9f0ae7b8ef8340"
            )
    );
}
