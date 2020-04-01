package com.github.poad.openjdk.finder.entity;

import org.springframework.data.annotation.Immutable;

import javax.persistence.*;

@Entity
@Table
@Immutable
public class JavaVersion {
    @Id
    @GeneratedValue
    private final String id;

    @Column(nullable = false)
    private final String vendor;

    @Column(nullable = false)
    private final int majorVersion;

    @Column(nullable = false)
    private final String arch;

    @Column(nullable = false)
    private final String version;

    @Column(nullable = false)
    private final String url;

    @Column(nullable = false)
    private final String type;

    @Column(nullable = false)
    private final String impl;

    @Column(nullable = false)
    private final String os;

    @Column(nullable = false)
    private final String timestamp;

    // for JPA
    public JavaVersion() {
        this(null, null, -1, null, null, null, null, null, null, null);
    }

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

    public String getImpl() {
        return impl;
    }

    public String getOs() {
        return os;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
