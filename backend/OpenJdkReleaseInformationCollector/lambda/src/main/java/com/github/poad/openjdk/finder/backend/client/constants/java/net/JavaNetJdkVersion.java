package com.github.poad.openjdk.finder.backend.client.constants.java.net;

import com.github.poad.openjdk.finder.backend.entity.JavaVersion;

public class JavaNetJdkVersion extends JavaVersion {
    public JavaNetJdkVersion(String hash, int majorVersion, int build, String arch, String version, String installationType, String extension, String os) {
        super(
                Vendor.JAVA_NET.getVendor(),
                Distribution.OPENJDK.getDistribution(),
                majorVersion,
                arch,
                version,
                installationType,
                extension,
                asDownloadUrl(hash, majorVersion, build, arch, version, extension, os),
                Type.HOTSPOT.getType(),
                os,
                Bundle.JDK.getBundle(),
                false,
                "sha256",
                asCheckSumUrl(hash, majorVersion, build, arch, version, extension, os),
                null,
                null);
    }

    private static String asDownloadUrl(String hash, int majorVersion, int build, String arch, String version, String extension, String os) {
        return String.join(
                "",
                "https://download.java.net/java/GA/jdk",
                Integer.toString(majorVersion),
                "/",
                hash,
                "/",
                Integer.toString(build),
                "/GPL/openjdk-",
                version,
                "-",
                os.equals("macos") ? "osx" : os,
                "_",
                arch,
                "_bin",
                ".",
                extension);
    }

    private static String asCheckSumUrl(String hash, int majorVersion, int build, String arch, String version, String extension, String os) {
        return String.join(".", asDownloadUrl(hash, majorVersion, build, arch, version, extension, os), "sha256");
    }
}
