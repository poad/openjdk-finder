package com.github.poad.openjdk.finder.backend.client.constants.adopt;

import com.github.poad.openjdk.finder.backend.entity.JavaVersion;

abstract class AdoptVersion extends JavaVersion {
    protected static final String DOWNLOAD_URL_PREFIX = "https://github.com/AdoptOpenJDK/";

    public AdoptVersion(String vendor, String distribution, int majorVersion, String arch, String version, String installationType, String extension, String url, String type, String os, String bundle, String checksum) {
        super(vendor, distribution, majorVersion, arch, version, installationType, extension, url, type, os, bundle, false, "sha256", checksum, null, null);
    }
}
