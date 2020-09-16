package com.github.poad.openjdk.finder.backend.client.constants.zulu;

import com.github.poad.openjdk.finder.backend.entity.JavaVersion;

class ZuluJavaVersion extends JavaVersion {
    public ZuluJavaVersion(String zuluVersion, int majorVersion, String arch, String version, String installationType, String extension, String os, String bundle, Boolean fx, String checksumType, String checksum) {
        super("zulu", "zulu", majorVersion, arch, version, installationType, extension, asDownloadUrl(zuluVersion, arch, version, extension, os, bundle, fx), "hotspot", os, bundle, fx, checksumType, checksum, null, null);
    }

    enum Arch {
        ARM32("arm"),
        ARM64("aarch64"),
        PPC("ppc"),
        X86("x86"),
        AMD64("x64"),
        SPARC_V9("sparcv9");

        private final String name;

        Arch(String name) {
            this.name = name;
        }
    }

    private static String asDownloadUrl(String zuluVersion, String arch, String version, String extension, String os, String bundle, Boolean fx) {
        return String.join(
                "",
                arch.equals(Arch.ARM64.name) ? "https://cdn.azul.com/zulu-embedded/bin/zulu" : "https://cdn.azul.com/zulu/bin/zulu",
                zuluVersion,
                "-ca-",
                fx ? "fx-" : "",
                bundle,
                version,
                "-",
                os.equals("windows") ? "win" : os, "_",
                arch.equals(Arch.X86.name) ? "i686" : arch,
                ".",
                extension);
    }
}
