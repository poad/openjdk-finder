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

    // for JPA
    public JavaVersion() {
        this(null, null, -1, null, null, null);
    }

    public JavaVersion(String id, String vendor, int majorVersion, String arch, String version, String url) {
        this.id = id;
        this.vendor = vendor;
        this.majorVersion = majorVersion;
        this.arch = arch;
        this.version = version;
        this.url = url;
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
}
