package com.github.poad.openjdk.finder.backend.client.constants.adopt;

import com.github.poad.openjdk.finder.backend.entity.JavaVersion;

import java.util.stream.Stream;

public class AdoptHotspotJre15Version extends AdoptHotspotJreVersion {
    public AdoptHotspotJre15Version(String arch, String installationType, String extension, String os, String checksum) {
        super(15, 36, arch, "15.0.0", installationType, extension, os, checksum);
    }

    public static final Stream<JavaVersion> VERSIONS = Stream.of(
            new AdoptHotspotJre15Version(
                    "x64",
                    "archive",
                    "tar.gz",
                    "linux",
                    "230d97a6b16a0735f15013a91f7582a22282ec12bdfaec291ab63274cc075efb"
            ),

            new AdoptHotspotJre15Version(
                    "x64",
                    "installer",
                    "msi",
                    "windows",
                    "d14250fb3ed06d6421c6b2e04ae266b436138213eea2fe4ddc3640df8064d9bc"
            ),
            new AdoptHotspotJre15Version(
                    "x64",
                    "archive",
                    "zip",
                    "windows",
                    "2f6586199ee50db32ce459cbf55f4a0da93dfd68986e1b75fee40aba8f52beec"
            ),

            new AdoptHotspotJre15Version(
                    "x64",
                    "installer",
                    "pkg",
                    "macos",
                    "e0d3070979b046a175f50c7c806b52218c7d378ef94243ba8e1674555d45864f"
            ),
            new AdoptHotspotJre15Version(
                    "x64",
                    "archive",
                    "tar.gz",
                    "macos",
                    "e5bd9da9bead8689a0a3c1077ab9d5da7d30b4be75c95c83e4dd5ad4b39af286"
            ),

            new AdoptHotspotJre15Version(
                    "x86",
                    "installer",
                    "msi",
                    "windows",
                    "06bdbe5ad4eac85752f0861c0cc367809cfaa56c69f55cb4001ae58dc2968f12"
            ),
            new AdoptHotspotJre15Version(
                    "x86",
                    "archive",
                    "zip",
                    "windows",
                    "7271f7f516354f3bfabb28e0fa67216776fc10668f6c6d3e17fb012f513f66a6"
            ),


            new AdoptHotspotJre15Version(
                    "aarch64",
                    "archive",
                    "tar.gz",
                    "linux",
                    "318f50bae6652d4468ee262ce0fd6569adbc461bea0d1ecce77ce2843efee8d4"
            ),

            new AdoptHotspotJre15Version(
                    "ppc64le",
                    "archive",
                    "tar.gz",
                    "linux",
                    "537eb289a0fc56915078ff92616574b00b8ad0119543f5e4a817ede0e52c4030"
            ),

            new AdoptHotspotJre15Version(
                    "s390x",
                    "archive",
                    "tar.gz",
                    "linux",
                    "e1e124a29e2bf892d267eb63d00dd136558b4e276bcb6741ea676c995b2fff51"
            ),

            new AdoptHotspotJre15Version(
                    "ppc64",
                    "archive",
                    "tar.gz",
                    "aix",
                    "4de08de427c64b37731be05d31e6ea2bfa84fef54ce3190783e3cd410c33c3c3"
            ),

            new AdoptHotspotJre15Version(
                    "arm32",
                    "archive",
                    "tar.gz",
                    "linux",
                    "09c1ba3636e7899d8b43795d7988bcc4b1e1be2919764d94f6d4a1a855ce774f"
            )

        );
}
