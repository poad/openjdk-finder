package com.github.poad.openjdk.finder.backend.entity;

public class JavaVersion {

    private final String id;

    private final String vendor;

    private final String distribution;

    private final int majorVersion;

    private final String arch;

    private final String version;

    private final String installationType;

    private final String extension;

    private final String url;

    private final String type;

    private final String os;

    private final String bundle;

    private final Boolean fx;

    private final String timestamp;

    public JavaVersion(String id, String vendor, String distribution, int majorVersion, String arch, String version, String installationType, String extension, String url, String type, String os, String bundle, Boolean fx, String timestamp) {
        this.id = id;
        this.vendor = vendor;
        this.distribution = distribution;
        this.majorVersion = majorVersion;
        this.arch = arch;
        this.version = version;
        this.installationType = installationType;
        this.extension = extension;
        this.url = url;
        this.type = type;
        this.os = os;
        this.bundle = bundle;
        this.fx = fx;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public String getVendor() {
        return vendor;
    }

    public String getDistribution() {
        return distribution;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public String getArch() {
        return arch;
    }

    public String getVersion() {
        return version;
    }

    public String getType() {
        return type;
    }

    public String getOs() {
        return os;
    }

    public String getBundle() {
        return bundle;
    }

    public Boolean getFx() {
        return fx;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getInstallationType() {
        return installationType;
    }

    public String getExtension() {
        return extension;
    }

    public String getUrl() {
        return url;
    }
}
