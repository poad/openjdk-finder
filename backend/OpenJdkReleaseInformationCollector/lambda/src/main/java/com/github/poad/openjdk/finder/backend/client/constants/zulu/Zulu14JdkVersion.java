package com.github.poad.openjdk.finder.backend.client.constants.zulu;

import com.github.poad.openjdk.finder.backend.entity.JavaVersion;

import java.util.stream.Stream;

public class Zulu14JdkVersion extends ZuluJavaVersion {
    Zulu14JdkVersion(String arch, String installationType, String extension, String os, String checksumType, String checksum) {
        super("14.29.23", 14, arch, "14.0.2", installationType, extension, os, "jdk", false, checksumType, checksum);
    }

    Zulu14JdkVersion(String zuluVersion, String arch, String version, String installationType, String extension, String os, String checksumType, String checksum) {
        super(zuluVersion, 14, arch, version, installationType, extension, os, "jdk", false, checksumType, checksum);
    }

    public static final Stream<JavaVersion> VERSIONS = Stream.of(
            new Zulu14JdkVersion(
                    "x64",
                    "archive",
                    "tar.gz",
                    "linux",
                    "sha256",
                    "7f4310a98ea0e52bacbec389012d859dbb51e759fe35a2cfebb11300271872d2"
            ),
            new Zulu14JdkVersion(
                    "x64",
                    "archive",
                    "tar.gz",
                    "linux_musl",
                    "sha256",
                    "24549b8b49fa85283baad81f1cdff8718d370a7f91e15995beb5a27c6d22e4ad"
            ),
            new Zulu14JdkVersion(
                    "x64",
                    "installer",
                    "rpm",
                    "linux",
                    "sha256",
                    "d5b17a15fb7afcde9586ee0d901f669285737d860127c8e86852eef2214e315b"
            ),
            new Zulu14JdkVersion(
                    "x64",
                    "installer",
                    "dep",
                    "linux",
                    "sha256",
                    "d5b17a15fb7afcde9586ee0d901f669285737d860127c8e86852eef2214e315b"
            ),
            // windows
            new Zulu14JdkVersion(
                    "x64",
                    "archive",
                    "zip",
                    "windows",
                    "sha256",
                    "281114bf5fb884f17f04ea2390e3e686535091a4922c59d2eb2b83fb0eec2e45"
            ),
            new Zulu14JdkVersion(
                    "x64",
                    "installer",
                    "msi",
                    "windows",
                    "sha256",
                    "e5c30ed6923deecdb34b1c563302e2b802be1e4ff1ed63b53144dcaf0d43229d"
            ),
            // macos
            new Zulu14JdkVersion(
                    "x64",
                    "archive",
                    "zip",
                    "macos",
                    "sha256",
                    "57e737d639a6927c82626fd18257751e6bb4d64e291b8fac62ddad01255a1d77"
            ),
            new Zulu14JdkVersion(
                    "x64",
                    "archive",
                    "tar.gz",
                    "macos",
                    "sha256",
                    "8f15f435c3e8d8a4bb1de441b1d7601fe64e1bafdcf0862e2962ae429ea9e6b2"
            ),
            new Zulu14JdkVersion(
                    "x64",
                    "installer",
                    "dmg",
                    "macos",
                    "sha256",
                    "8ed14e3a3b8fabb799716e4cebf1cf0ac6033c5f5c7597aa659c19d4459ebc7b"
            ),

            // 32bit
            new Zulu14JdkVersion(
                    "x86",
                    "archive",
                    "tar.gz",
                    "linux",
                    "sha256",
                    "e1111709c5fb1ecabff8080f7974ffba0b782246a77272f2fcbedd2d15adee45"
            ),
            new Zulu14JdkVersion(
                    "x86",
                    "archive",
                    "zip",
                    "windows",
                    "sha256",
                    "c43cc544758c041234146791d52f340bf19ec6186529c625d685f766b716c9be"
            )
    );
}
