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
    private final String distribution;

    @Column(nullable = false)
    private final int majorVersion;

    @Column(nullable = false)
    private final String arch;

    @Column(nullable = false)
    private final String version;

    @Column(nullable = false)
    private final String installationType;

    @Column
    private final String extension;

    @Column(nullable = false)
    private final String url;

    @Column(nullable = false)
    private final String type;

    @Column(nullable = false)
    private final String bundle;

    @Column(nullable = false)
    private final Boolean fx;

    @Column(nullable = false)
    private final String os;

    @Column
    private final String checksumType;

    @Column
    private final String checksum;

    @Column
    private final String sigUrl;

    @Column
    private final String timestamp;

    // for JPA
    public JavaVersion() {
        this(null, null, null, -1, null, null,null, null, null, null, null, null, null, null, null, null, null);
    }

    public JavaVersion(String id, String vendor, String distribution, int majorVersion, String arch, String version, String installationType, String extension, String url, String type, String bundle, Boolean fx, String os, String checksumType, String checksum, String sigUrl, String timestamp) {
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
        this.bundle = bundle;
        this.fx = fx;
        this.os = os;
        this.checksumType = checksumType;
        this.checksum = checksum;
        this.sigUrl = sigUrl;
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

    public String getInstallationType() {
        return installationType;
    }

    public String getExtension() {
        return extension;
    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    public String getBundle() {
        return bundle;
    }

    public Boolean getFx() {
        return fx;
    }

    public String getOs() {
        return os;
    }

    public String getChecksumType() {
        return checksumType;
    }

    public String getChecksum() {
        return checksum;
    }

    public String getSigUrl() {
        return sigUrl;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
