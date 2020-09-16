package com.github.poad.openjdk.finder.backend.client.constants.zulu;

import com.github.poad.openjdk.finder.backend.entity.JavaVersion;

import java.util.stream.Stream;

public class Zulu15JreVersion extends ZuluJavaVersion {
    public Zulu15JreVersion(String arch, String installationType, String extension, String os, String checksumType, String checksum) {
        super("15.27.17", 15, arch, "15.0.0", installationType, extension, os, "jre", false, checksumType, checksum);
    }

    public Zulu15JreVersion(String zuluVersion, String arch, String version, String installationType, String extension, String os, String checksumType, String checksum) {
        super(zuluVersion, 15, arch, version, installationType, extension, os, "jre", false, checksumType, checksum);
    }

    static final Stream<JavaVersion> JDK14 = Stream.of(
            new Zulu15JreVersion(
                    "aarch64",
                    "archive",
                    "tar.gz",
                    "linux",
                    "sha256",
                    "248ef093ae0aaed88e6cbfd4dee725bf89577c2854eaa7164ca68a7a29df50d2"
            ),
            new Zulu15JreVersion(
                    "x64",
                    "archive",
                    "tar.gz",
                    "linux_musl",
                    "sha256",
                    "646f89e0bf578c1b96cefa5999b55195afd55530e775f243494616dd863f609a"
            ),
            new Zulu15JreVersion(
                    "x64",
                    "archive",
                    "tar.gz",
                    "linux",
                    "sha256",
                    "ddafe5a8bb2fd600d7a63a968910f190e596ea1a55d4c31bb4a4474ef4b65c5b"
            ),
            new Zulu15JreVersion(
                    "x64",
                    "installer",
                    "rpm",
                    "linux",
                    "sha256",
                    "4dbfa3528c9484cd12be71b9e9080e87dfc26a85a0c9ca860f195e1143802c8e"
            ),
            new Zulu15JreVersion(
                    "x64",
                    "installer",
                    "dep",
                    "linux",
                    "sha256",
                    "43a7851af14c96eb75bcca704630cc47af3922d2af5b4ffcc76a06555741d34e"
            ),
            new Zulu15JreVersion(
                    "x64",
                    "archive",
                    "zip",
                    "windows",
                    "sha256",
                    "e08ba7b02d93ab3438b56be1c10327959f3e0d7a4dd2b445f32aa17174067c8d"
            ),
            new Zulu15JreVersion(
                    "x64",
                    "installer",
                    "msi",
                    "windows",
                    "sha256",
                    "35754c9a93712b28053953655af49ffe0aad9b77738ea5e02fa5cd1150349ed9"
            ),
            new Zulu15JreVersion(
                    "x64",
                    "archive",
                    "zip",
                    "macos",
                    "sha256",
                    "f7d6d3f5771be67c12782d1f714877ece9caa06b7f2e79bc0cc9f9ac74c5c910"
            ),
            new Zulu15JreVersion(
                    "x64",
                    "installer",
                    "dmg",
                    "macos",
                    "sha256",
                    "7e70cb36935fe99221e737292e7b08ab92724bbc8a070498a20626999121e568"
            ),
            new Zulu15JreVersion(
                    "x64",
                    "archive",
                    "tar.gz",
                    "macos",
                    "sha256",
                    "4ad903a2b7f0b5646838ced83809a5a5dc4d9014cbdbff794611036ced91a7d4"
            ),

            new Zulu15JreVersion(
                    "x86",
                    "archive",
                    "tar.gz",
                    "linux",
                    "sha256",
                    "387ac0c807dbe4e4772e61a1b5508cc700f57cb19f3cee2a80bc38caf3e6da32"
            ),
            new Zulu15JreVersion(
                    "x86",
                    "archive",
                    "zip",
                    "windows",
                    "sha256",
                    "1b64a9432245dd418ca7c6a0dff9480d0cd550fa914f526ca69a0f68010e0325"
            ),
            new Zulu15JreVersion(
                    "x86",
                    "installer",
                    "msi",
                    "windows",
                    "sha256",
                    "eecc04aa5fdcd33cc4e75038e36067f27b96294a027d0d6ee3062b05e234805c"
            )
    );
}
