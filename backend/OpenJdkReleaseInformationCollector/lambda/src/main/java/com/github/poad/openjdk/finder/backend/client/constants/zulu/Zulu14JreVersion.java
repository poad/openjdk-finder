package com.github.poad.openjdk.finder.backend.client.constants.zulu;

import com.github.poad.openjdk.finder.backend.entity.JavaVersion;

import java.util.stream.Stream;

public class Zulu14JreVersion extends ZuluJavaVersion {
    Zulu14JreVersion(String arch, String installationType, String extension, String os, String checksumType, String checksum) {
        super("14.29.23", 14, arch, "14.0.2", installationType, extension, os, "jre", false, checksumType, checksum);
    }

    Zulu14JreVersion(String zuluVersion, String arch, String version, String installationType, String extension, String os, String checksumType, String checksum) {
        super(zuluVersion, 14, arch, version, installationType, extension, os, "jre", false, checksumType, checksum);
    }

    public static final Stream<JavaVersion> VERSIONS = Stream.of(
            new Zulu14JreVersion(
                    "x64",
                    "archive",
                    "tar.gz",
                    "linux",
                    "sha256",
                    "5fa71299c4f712141ffe60a2ac837557c0e12c059141d8208486a9bdbc213366"
            ),
            new Zulu14JreVersion(
                    "x64",
                    "archive",
                    "tar.gz",
                    "linux_musl",
                    "sha256",
                    "1c6d9f351765cbd5e2a26141ce515d8ff2e4ba892fda249a84cd2296089d6478"
            ),
            // windows
            new Zulu14JreVersion(
                    "x64",
                    "archive",
                    "zip",
                    "windows",
                    "sha256",
                    "3b70ff0127dc3471cc542a3dc6a66036d64d2abf3b2a28637934f708f4cc308c"
            ),
            // macos
            new Zulu14JreVersion(
                    "x64",
                    "archive",
                    "tar.gz",
                    "macos",
                    "sha256",
                    "f69fbf3874b5c47fa2b13a0c7755e02a868c1e65897ecb13a8c4736dc564f7c1"
            ),

            // 32bit
            new Zulu14JreVersion(
                    "x86",
                    "archive",
                    "tar.gz",
                    "linux",
                    "sha256",
                    "0744ab3a68d81a8c0ed4fb752f40de48be76eb8d8979c696fd5413183bd32df8"
            ),
            new Zulu14JreVersion(
                    "x86",
                    "archive",
                    "zip",
                    "windows",
                    "sha256",
                    "eb9333a2bd8c9e46a7451ecff8bf62c6c0a2a5bd5308c7a478a0d0f5c312e399"
            )
    );
}
