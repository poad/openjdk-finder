package com.github.poad.openjdk.finder.backend.entity;

@SuppressWarnings("unused")
public class JavaVersion {

    public enum Type {
        HOTSPOT("hostspot"),
        OPENJ9("openj9");

        private final String type;
        Type(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public enum Bundle {
        JDK("jdk"),
        JRE("jre");

        private final String bundle;
        Bundle(String bundle) {
            this.bundle = bundle;
        }

        public String getBundle() {
            return bundle;
        }
    }

    public enum Distribution {
        ADOPT("adoptopenjdk"),
        OPENJDK("openjdk"),
        ZULU("zulu");

        private final String distribution;
        Distribution(String distribution) {
            this.distribution = distribution;
        }

        public String getDistribution() {
            return distribution;
        }
    }

    public enum Vendor {
        ADOPT("adoptopenjdk"),
        JAVA_NET("java.net"),
        ZULU("zulu");

        private final String vendor;
        Vendor(String vendor) {
            this.vendor = vendor;
        }

        public String getVendor() {
            return vendor;
        }
    }

    public enum Arch {
        ARM32("arm"),
        ARM64("aarch64"),
        PPC("ppc"),
        X86("x86"),
        AMD64("x64"),
        SPARC_V9("sparcv9");

        private final String arch;

        Arch(String arch) {
            this.arch = arch;
        }

        public String getArch() {
            return this.arch;
        }
    }

    public enum OS {
        LINUX("linux"),
        LINUX_MUSL("linux_musl"),
        MACOS("macos"),
        WINDOWS("windows"),
        SOLARIS("solaris");

        private final String os;

        OS(String os) {
            this.os = os;
        }

        public String getOs() {
            return this.os;
        }
    }

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

    private final String checksumType;

    private final String checksum;

    private final String sigUrl;

    private final String timestamp;


    public JavaVersion(String vendor, String distribution, int majorVersion, String arch, String version, String installationType, String extension, String url, String type, String os, String bundle, Boolean fx, String checksumType, String checksum, String sigUrl, String timestamp) {
        this(genId(vendor, version, bundle, type, arch, extension, os), vendor, distribution, majorVersion, arch, version, installationType, extension, url, type, os, bundle, fx, checksumType, checksum, sigUrl, timestamp);
    }

    public JavaVersion(String id, String vendor, String distribution, int majorVersion, String arch, String version, String installationType, String extension, String url, String type, String os, String bundle, Boolean fx, String checksumType, String checksum, String sigUrl, String timestamp) {
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

    public String getType() {
        return type;
    }

    public String getOs() {
        return os;
    }

    public String getBundle() {
        return bundle;
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

    public String getChecksumType() {
        return checksumType;
    }

    public String getChecksum() {
        return checksum;
    }

    public String getSigUrl() {
        return sigUrl;
    }

    public Boolean getFx() {
        return fx;
    }

    public String getTimestamp() {
        return timestamp;
    }


    private static String genId(String vendor, String version, String bundle, String type, String arch, String ext, String os) {
        return String.join("-", vendor, version, bundle, type, arch, ext, os);
    }
}
