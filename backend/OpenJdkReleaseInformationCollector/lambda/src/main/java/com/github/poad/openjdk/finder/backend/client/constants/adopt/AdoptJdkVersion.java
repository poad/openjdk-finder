package com.github.poad.openjdk.finder.backend.client.constants.adopt;

abstract class AdoptJdkVersion extends AdoptVersion {
    public AdoptJdkVersion(String vendor, int majorVersion, String arch, String version, String installationType, String extension, String url, String type, String os, String checksum) {
        super(vendor, Distribution.ADOPT.getDistribution(), majorVersion, arch, version, installationType, extension, url, type, os, Bundle.JDK.getBundle(), checksum);
    }
}
