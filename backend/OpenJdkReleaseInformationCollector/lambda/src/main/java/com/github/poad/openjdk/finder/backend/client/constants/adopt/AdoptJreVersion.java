package com.github.poad.openjdk.finder.backend.client.constants.adopt;

abstract class AdoptJreVersion extends AdoptVersion {
    public AdoptJreVersion(String vendor, int majorVersion, String arch, String version, String installationType, String extension, String url, String type, String os, String checksum) {
        super(vendor, Distribution.ADOPT.getDistribution(), majorVersion, arch, version, installationType, extension, url, type, os, Bundle.JRE.getBundle(), checksum);
    }
}
