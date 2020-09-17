package com.github.poad.openjdk.finder.backend.client.constants.adopt;

abstract class AdoptOpenJ9JreVersion extends AdoptJreVersion {
    public AdoptOpenJ9JreVersion(int majorVersion, int build, String j9Version, String arch, String version, String installationType, String extension, String os, String checksum) {
        super(
                Vendor.ADOPT.getVendor(),
                majorVersion,
                arch,
                version,
                installationType,
                extension,
                asDownloadURL(
                        majorVersion,
                        build,
                        j9Version,
                        version,
                        arch,
                        os,
                        extension
                ),
                Type.OPENJ9.getType(),
                os,
                checksum);
    }

    protected static String asDownloadURL(int majorVersion, int build, String j9Version, String version, String arch, String os, String extension) {
        return String.join(
                "",
                DOWNLOAD_URL_PREFIX,
                "openjdk",
                Integer.toString(majorVersion),
                "-",
                "binaries/releases/download/jdk-",
                version,
                "%2B",
                Integer.toString(build),
                "_",
                "openj9",
                "-",
                j9Version,
                "/OpenJDK",
                Integer.toString(majorVersion),
                "U-",
                "jre",
                "_",
                arch,
                "_",
                os,
                "_",
                "openj9",
                "_",
                version,
                "_",
                Integer.toString(build),
                "_",
                "openj9",
                "-",
                j9Version,
                ".",
                extension);
    }
}
