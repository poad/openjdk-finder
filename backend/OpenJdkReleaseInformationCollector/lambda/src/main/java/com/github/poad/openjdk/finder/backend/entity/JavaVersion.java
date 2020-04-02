package com.github.poad.openjdk.finder.backend.entity;

public class JavaVersion {

    private final String id;

    private final String vendor;

    private final int majorVersion;

    private final String arch;

    private final String version;

    private final String url;

    private final String type;

    private final String os;

    private final String impl;

    private final String timestamp;

    public JavaVersion(String id, String vendor, int majorVersion, String arch, String version, String url, String type, String impl, String os, String timestamp) {
        this.id = id;
        this.vendor = vendor;
        this.majorVersion = majorVersion;
        this.arch = arch;
        this.version = version;
        this.url = url;
        this.type = type;
        this.impl = impl;
        this.os = os;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public String getVendor() {
        return vendor;
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

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    public String getOs() {
        return os;
    }

    public String getImpl() {
        return impl;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
