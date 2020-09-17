package com.github.poad.openjdk.finder.backend.client.constants.adopt;

public class AdoptHotspotJdkVersion extends AdoptJdkVersion {
    public AdoptHotspotJdkVersion(int majorVersion, int build, String arch, String version, String installationType, String extension, String os, String checksum) {
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
                    version,
                    arch,
                    os,
                    extension
                ),
                Type.HOTSPOT.getType(),
                os,
                checksum);
    }

    private static String asDownloadURL(int majorVersion, int build, String version, String arch, String os, String extension)  {
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
                    "/OpenJDK",
                    Integer.toString(majorVersion),
                    "U-",
                    "jdk",
                    "_",
                    arch,
                    "_",
                    os,
                    "_",
                    "hotspot",
                    "_",
                    version,
                    "_",
                    Integer.toString(build),
                    ".",
                    extension);
    }
}
