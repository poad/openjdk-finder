package com.github.poad.openjdk.finder.backend.client.constants.adopt;

import com.github.poad.openjdk.finder.backend.entity.JavaVersion;

import java.util.stream.Stream;

public class AdoptOpenJ9Jdk14Version extends AdoptOpenJ9JdkVersion {
    public AdoptOpenJ9Jdk14Version(String arch, String installationType, String extension, String os, String checksum) {
        super(14, 12, "0.21.0", arch, "14.0.2", installationType, extension, os, checksum);
    }

    public static final Stream<JavaVersion> VERSIONS = Stream.of(
        new AdoptOpenJ9Jdk14Version(
                "x64",
                "archive",
                "tar.gz",
                "linux",
                "c198593d1b5188ee3570e2ca33c3bc004aaefbda2c11e68e58ae7296cf5c3982"
        )
    );
}
