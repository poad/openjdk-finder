package com.github.poad.openjdk.finder.backend.client.constants.zulu;

import com.github.poad.openjdk.finder.backend.entity.JavaVersion;

import java.util.stream.Stream;

public class Zulu14JdkFxVersion extends ZuluJavaVersion {
    Zulu14JdkFxVersion(String arch, String installationType, String extension, String os, String checksumType, String checksum) {
        super("14.29.23", 14, arch, "14.0.2", installationType, extension, os, "jdk", true, checksumType, checksum);
    }

    Zulu14JdkFxVersion(String zuluVersion, String arch, String version, String installationType, String extension, String os, String checksumType, String checksum) {
        super(zuluVersion, 14, arch, version, installationType, extension, os, "jdk", true, checksumType, checksum);
    }

    public static final Stream<JavaVersion> VERSIONS = Stream.of(
            new Zulu14JdkFxVersion(
                    "x64",
                    "archive",
                    "tar.gz",
                    "linux",
                    "sha256",
                    "502f7c9b93affb46eed532b5c18277bb5747987ce7356c21514ef630634788cc"
            ),
            // windows
            new Zulu14JdkFxVersion(
                    "x64",
                    "archive",
                    "zip",
                    "windows",
                    "sha256",
                    "e3d4755a1fb63df531d00fbaad6d77ef56acb9b5fa6a2f0540f0a95ce735ae3b"
            ),
            // macos
            new Zulu14JdkFxVersion(
                    "x64",
                    "archive",
                    "tar.gz",
                    "macos",
                    "sha256",
                    "a9f921ff541f7fe48fac11642caf99f77d10aeda3aeff14e79ade7dd54845067"
            )
    );
}
