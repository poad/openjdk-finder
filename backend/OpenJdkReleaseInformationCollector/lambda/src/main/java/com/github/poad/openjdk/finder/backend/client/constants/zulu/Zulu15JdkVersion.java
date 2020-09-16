package com.github.poad.openjdk.finder.backend.client.constants.zulu;

import com.github.poad.openjdk.finder.backend.entity.JavaVersion;

import java.util.stream.Stream;

public class Zulu15JdkVersion extends ZuluJavaVersion {
    public Zulu15JdkVersion(String arch, String installationType, String extension, String os, String checksumType, String checksum) {
        super("15.27.17", 15, arch, "15.0.0", installationType, extension, os, "jdk", false, checksumType, checksum);
    }

    public Zulu15JdkVersion(String zuluVersion, String arch, String version, String installationType, String extension, String os, String checksumType, String checksum) {
        super(zuluVersion, 15, arch, version, installationType, extension, os, "jdk", false, checksumType, checksum);
    }

    static final Stream<JavaVersion> JDK14 = Stream.of(
            new Zulu15JdkVersion(
                    "aarch64",
                    "archive",
                    "tar.gz",
                    "linux",
                    "sha256",
                    "aadb8303b975b757049face3086c57147c0089f83d7c065f117e8d664b41314b"
            ),
            new Zulu15JdkVersion(
                    "x64",
                    "archive",
                    "tar.gz",
                    "linux_musl",
                    "sha256",
                    "f160e4dc369d00e238cfe12ba423ff063533649159a0d49df022ce6eddff8370"
            ),
            new Zulu15JdkVersion(
                    "x64",
                    "archive",
                    "tar.gz",
                    "linux",
                    "sha256",
                    "0a38f1138c15a4f243b75eb82f8ef40855afcc402e3c2a6de97ce8235011b1ad"
            ),
            new Zulu15JdkVersion(
                    "x64",
                    "installer",
                    "rpm",
                    "linux",
                    "sha256",
                    "a9f921ff541f7fe48fac11642caf99f77d10aeda3aeff14e79ade7dd54845067"
            ),
            new Zulu15JdkVersion(
                    "x64",
                    "installer",
                    "dep",
                    "linux",
                    "sha256",
                    "11a34b52d1b0045d33c0ed0e4bd98dbd82ff3535f3c7befd15e4a8763f1f7979"
            ),
            new Zulu15JdkVersion(
                    "x64",
                    "archive",
                    "zip",
                    "windows",
                    "sha256",
                    "f535a530151e6c20de8a3078057e332b08887cb3ba1a4735717357e72765cad6"
            ),
            new Zulu15JdkVersion(
                    "x64",
                    "installer",
                    "msi",
                    "windows",
                    "sha256",
                    "9fda2c1a73c5b183af282bd065bb075de94fd17b450580e99d174f3053feb9aa"
            ),
            new Zulu15JdkVersion(
                    "x64",
                    "archive",
                    "zip",
                    "macos",
                    "sha256",
                    "2f6f989d673eeed56680a088aeddcc17d7bc175bb443e8754051c2383ec17077"
            ),
            new Zulu15JdkVersion(
                    "x64",
                    "installer",
                    "dmg",
                    "macos",
                    "sha256",
                    "555dc9791fd3d9ea276ea273c5b2bc31224a02992eeb60f7d772780df21eef7d"
            ),
            new Zulu15JdkVersion(
                    "x64",
                    "archive",
                    "tar.gz",
                    "macos",
                    "sha256",
                    "f80b2e0512d9d8a92be24497334c974bfecc8c898fc215ce0e76594f00437482"
            ),

            new Zulu15JdkVersion(
                    "x86",
                    "archive",
                    "tar.gz",
                    "linux",
                    "sha256",
                    "387ac0c807dbe4e4772e61a1b5508cc700f57cb19f3cee2a80bc38caf3e6da32"
            ),
            new Zulu15JdkVersion(
                    "x86",
                    "archive",
                    "zip",
                    "windows",
                    "sha256",
                    "ae00d9c0ba5d07f7bc423cdc83c7956cb4dfac4bd01725aeb4b2c0e940a10a68"
            )
    );
}
