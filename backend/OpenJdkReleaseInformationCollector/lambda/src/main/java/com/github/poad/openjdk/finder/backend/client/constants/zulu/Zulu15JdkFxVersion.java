package com.github.poad.openjdk.finder.backend.client.constants.zulu;

import com.github.poad.openjdk.finder.backend.entity.JavaVersion;

import java.util.stream.Stream;

public class Zulu15JdkFxVersion extends ZuluJavaVersion {
    public Zulu15JdkFxVersion(String arch, String installationType, String extension, String os, String checksumType, String checksum) {
        super("15.27.17", 15, arch, "15.0.0", installationType, extension, os, "jdk", true, checksumType, checksum);
    }

    public Zulu15JdkFxVersion(String zuluVersion, String arch, String version, String installationType, String extension, String os, String checksumType, String checksum) {
        super(zuluVersion, 15, arch, version, installationType, extension, os, "jdk", true, checksumType, checksum);
    }

    static final Stream<JavaVersion> JDK14 = Stream.of(
            new Zulu15JdkFxVersion(
                    "x64",
                    "archive",
                    "tar.gz",
                    "linux",
                    "sha256",
                    "fbe677047325112b5a570f21757589fb0dbe147fdbebf0f3100e843a5ed75f18"
            ),
            new Zulu15JdkFxVersion(
                    "x64",
                    "archive",
                    "zip",
                    "windows",
                    "sha256",
                    "1acb79eee62b0b9d11144463ff206ef661140960c58f49c92783ca1cfff50c53"
            ),
            new Zulu15JdkFxVersion(
                    "x64",
                    "archive",
                    "tar.gz",
                    "macos",
                    "sha256",
                    "736794c42678e0758cd8d097effec1f5bb58d212516c4a3110aac3779ca59143"
            )
    );
}
