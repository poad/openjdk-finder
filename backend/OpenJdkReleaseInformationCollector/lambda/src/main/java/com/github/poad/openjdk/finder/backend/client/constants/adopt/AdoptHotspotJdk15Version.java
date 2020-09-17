package com.github.poad.openjdk.finder.backend.client.constants.adopt;

import com.github.poad.openjdk.finder.backend.entity.JavaVersion;

import java.util.stream.Stream;

public class AdoptHotspotJdk15Version extends AdoptHotspotJdkVersion {
    public AdoptHotspotJdk15Version(String arch, String installationType, String extension, String os, String checksum) {
        super( 15, 36, arch, "15.0.0", installationType, extension, os, checksum);
    }

    public static final Stream<JavaVersion> VERSIONS = Stream.of(
            new AdoptHotspotJdk15Version(
                    "x64",
                    "archive",
                    "tar.gz",
                    "linux",
                    "c198593d1b5188ee3570e2ca33c3bc004aaefbda2c11e68e58ae7296cf5c3982"
            ),

            new AdoptHotspotJdk15Version(
                    "x64",
                    "installer",
                    "msi",
                    "windows",
                    "e2ac92e686e52d8c76a831319ad721547d811d2e2e9b3a8fae47420652beb930"
            ),
            new AdoptHotspotJdk15Version(
                    "x64",
                    "archive",
                    "zip",
                    "windows",
                    "0ec6b009c00e3786a30fd2327973ba8971817e482f658c732bea5cb9e268998f"
            ),

            new AdoptHotspotJdk15Version(
                    "x64",
                    "installer",
                    "pkg",
                    "macos",
                    "10ced23791428f002940c91c6d86aec3e701faf4478ddbb29a81ea3977a955b4"
            ),
            new AdoptHotspotJdk15Version(
                    "x64",
                    "archive",
                    "tar.gz",
                    "macos",
                    "bd1fc774232e2dfee93056a01f5765bd92ffb19d68dd548c233a82bb5c162be4"
            ),

            new AdoptHotspotJdk15Version(
                    "x86",
                    "installer",
                    "msi",
                    "windows",
                    "a04fdc73fe834213a6e9422e29dba771503d26d45e66413cbfe68b9c9c53289c"
            ),
            new AdoptHotspotJdk15Version(
                    "x86",
                    "archive",
                    "zip",
                    "windows",
                    "44a1ec8ca0dbe9912cb6043c43024c19804c6dad5b7868afe0095e7576f0ad7e"
            ),


            new AdoptHotspotJdk15Version(
                    "aarch64",
                    "archive",
                    "tar.gz",
                    "linux",
                    "44c8cd580f6e828f8fbb431a59b3c8f694da8c75adb7c311f7b6b9ed81c19c54"
            ),

            new AdoptHotspotJdk15Version(
                    "ppc64le",
                    "archive",
                    "tar.gz",
                    "linux",
                    "537eb289a0fc56915078ff92616574b00b8ad0119543f5e4a817ede0e52c4030"
            ),

            new AdoptHotspotJdk15Version(
                    "s390x",
                    "archive",
                    "tar.gz",
                    "linux",
                    "3e238ea4924f4dd2a8167c911a99b96ffdfb51e12f3969cffbdc0791a58d0cf2"
            ),

            new AdoptHotspotJdk15Version(
                    "ppc64",
                    "archive",
                    "tar.gz",
                    "aix",
                    "54ded86bb615de54858535f168390df1926dd183ce40479d842c6759dda05c2d"
            ),

            new AdoptHotspotJdk15Version(
                    "arm32",
                    "archive",
                    "tar.gz",
                    "linux",
                    "d7de37fee91fe098791d48ea2a880cf2789949665d6bc9a232380738f99c16a9"
            )

        );
}
