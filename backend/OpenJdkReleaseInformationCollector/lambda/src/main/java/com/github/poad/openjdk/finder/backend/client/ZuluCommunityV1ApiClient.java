package com.github.poad.openjdk.finder.backend.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.github.poad.openjdk.finder.backend.entity.JavaVersion;

import java.io.UncheckedIOException;
import java.net.http.HttpClient;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ZuluCommunityV1ApiClient extends JsonHttpClient implements OpenJdkApiClient {
    private static class Endpoint {
        private static final String LIST = "https://api.azul.com/zulu/download/community/v1.0/bundles/";
    }

    @SuppressWarnings("unused")
    private enum OS {
        LINUX("linux"),
        LINUX_MUSL("linux_musl"),
        MACOS("macos"),
        //        QNX("qnx"),
        WINDOWS("windows"),
        SOLARIS("solaris");

        private final String name;

        OS(String name) {
            this.name = name;
        }
    }

    @SuppressWarnings("unused")
    private enum Arch {
        ARM("arm"),
        //        MIPS("mips"),
        PPC("ppc"),
        X86("x86"),
        SPARC_V9("sparcv9");

        private final String name;

        Arch(String name) {
            this.name = name;
        }
    }

    private enum HwBitness {
        Bit32("32"),
        Bit64("64");

        private final String bitness;

        HwBitness(String bitness) {
            this.bitness = bitness;
        }
    }

    private enum Ext {
        //        CAB("cab", "archive"),
        DEB("deb", "installer"),
        RPM("rpm", "installer"),
        MSI("msi", "installer"),
        DMG("dmg", "installer"),
        TARBALL("tar.gz", "archive"),
        ZIP("zip", "archive");

        private final String ext;
        private final String type;

        Ext(String ext, String type) {
            this.ext = ext;
            this.type = type;
        }

        static Optional<Ext> fromExt(String ext) {
            return Arrays.stream(values()).filter(value -> value.ext.equals(ext)).findFirst();
        }
    }

    @SuppressWarnings("unused")
    private enum Whitelist {
        JDK_X64_WINDOWS_MSI("jdk-hotspot-x64-msi-windows"),
        JDK_X64_WINDOWS_ZIP("jdk-hotspot-x64-zip-windows"),
        JDK_X64_WINDOWS_MSI_FX("jdk-hotspot-x64-fx-msi-windows"),
        JDK_X64_WINDOWS_ZIP_FX("jdk-hotspot-x64-fx-zip-windows"),
        JRE_X64_WINDOWS_MSI("jre-hotspot-x64-msi-windows"),
        JRE_X64_WINDOWS_ZIP("jre-hotspot-x64-zip-windows"),
        JRE_X64_WINDOWS_MSI_FX("jre-hotspot-x64-fx-msi-windows"),
        JRE_X64_WINDOWS_ZIP_FX("jre-hotspot-x64-fx-zip-windows"),

        JDK_X64_LINUX_DEB("jdk-hotspot-x64-deb-linux"),
        JDK_X64_LINUX_RPM("jdk-hotspot-x64-rpm-linux"),
        JDK_X64_LINUX_ZIP("jdk-hotspot-x64-zip-linux"),
        JDK_X64_LINUX_TARBALL("jdk-hotspot-x64-tar.gz-linux"),
        JDK_X64_LINUX_TARBALL_FX("jdk-hotspot-x64-fx-tar.gz-linux"),
        JRE_X64_LINUX_DEB("jre-hotspot-x64-deb-linux"),
        JRE_X64_LINUX_RPM("jre-hotspot-x64-rpm-linux"),
        JRE_X64_LINUX_ZIP("jre-hotspot-x64-zip-linux"),
        JRE_X64_LINUX_TARBALL("jre-hotspot-x64-tar.gz-linux"),
        JRE_X64_LINUX_TARBALL_FX("jre-hotspot-x64-fx-tar.gz-linux"),

        JDK_X86_WINDOWS_MSI("jdk-hotspot-x86-msi-windows"),
        JDK_X86_WINDOWS_ZIP("jdk-hotspot-x86-zip-windows"),
        JDK_X86_WINDOWS_MSI_FX("jdk-hotspot-x86-fx-msi-windows"),
        JDK_X86_WINDOWS_ZIP_FX("jdk-hotspot-x86-fx-zip-windows"),
        JRE_X86_WINDOWS_MSI("jre-hotspot-x86-msi-windows"),
        JRE_X86_WINDOWS_ZIP("jre-hotspot-x86-zip-windows"),
        JRE_X86_WINDOWS_MSI_FX("jre-hotspot-x86-fx-msi-windows"),
        JRE_X86_WINDOWS_ZIP_FX("jre-hotspot-x86-fx-zip-windows"),

        JDK_X86_LINUX_DEB("jdk-hotspot-x86-deb-linux"),
        JDK_X86_LINUX_RPM("jdk-hotspot-x86-rpm-linux"),
        JDK_X86_LINUX_ZIP("jdk-hotspot-x86-zip-linux"),
        JDK_X86_LINUX_TARBALL("jdk-hotspot-x86-tar.gz-linux"),
        JDK_X86_LINUX_TARBALL_FX("jdk-hotspot-x86-fx-tar.gz-linux"),
        JRE_X86_LINUX_DEB("jre-hotspot-x86-deb-linux"),
        JRE_X86_LINUX_RPM("jre-hotspot-x86-rpm-linux"),
        JRE_X86_LINUX_ZIP("jre-hotspot-x86-zip-linux"),
        JRE_X86_LINUX_TARBALL("jre-hotspot-x86-tar.gz-linux"),
        JRE_X86_LINUX_TARBALL_FX("jre-hotspot-x86-fx-tar.gz-linux"),

        JDK_X64_MACOS_DMG("jdk-hotspot-x64-dmg-macos"),
        JDK_X64_MACOS_ZIP("jdk-hotspot-x64-zip-macos"),
        JDK_X64_MACOS_TARBALL("jdk-hotspot-x64-tar.gz-macos"),
        JDK_X64_MACOS_ZIP_FX("jdk-hotspot-x64-fx-zip-macos"),
        JDK_X64_MACOS_TARBALL_FX("jdk-hotspot-x64-fx-tar.gz-macos"),
        JRE_X64_MACOS_DMG("jre-hotspot-x64-dmg-macos"),
        JRE_X64_MACOS_ZIP("jre-hotspot-x64-zip-macos"),
        JRE_X64_MACOS_TARBALL("jre-hotspot-x64-tar.gz-macos"),
        JRE_X64_MACOS_ZIP_FX("jre-hotspot-x64-fx-zip-macos"),
        JRE_X64_MACOS_TARBALL_FX("jre-hotspot-x64-fx-tar.gz-macos"),

        JDK_X64_SOLARIS_ZIP("jdk-hotspot-x64-zip-solaris"),
        JDK_SPARK_SOLARIS_ZIP("jdk-hotspot-sparkv6-zip-solaris"),
        ;

        private final String pattern;

        Whitelist(String pattern) {
            this.pattern = pattern;
        }
    }

    @SuppressWarnings("unused")
    private enum Bundle {
        JDK("jdk"),
        JRE("jre");

        private final String name;

        Bundle(String name) {
            this.name = name;
        }
    }

    static class BundleDescription {
        private final Integer id;
        private final String name;
        private final List<Integer> jdkVersion;
        private final String url;
        private final List<Integer> zuluVersion;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        BundleDescription(
                @JsonProperty("id") Integer id,
                @JsonProperty("name") String name,
                @JsonProperty("url") String url,
                @JsonProperty("jdk_version") List<Integer> jdkVersion,
                @JsonProperty("zulu_version") List<Integer> zuluVersion) {
            this.id = id;
            this.name = name;
            this.jdkVersion = jdkVersion;
            this.url = url;
            this.zuluVersion = zuluVersion;
        }

        @SuppressWarnings("unused")
        public Integer getId() {
            return id;
        }

        @SuppressWarnings("unused")
        public String getName() {
            return name;
        }

        @SuppressWarnings("unused")
        public List<Integer> getJdkVersion() {
            return jdkVersion;
        }

        @SuppressWarnings("unused")
        public String getUrl() {
            return url;
        }

        @SuppressWarnings("unused")
        public List<Integer> getZuluVersion() {
            return zuluVersion;
        }
    }


    static class BundleDescriptionDetail {
        private final Integer id;
        private final String arch;
        private final String hwBitness;
        private final String os;
        private final String ext;
        private final String bundleType;
        private final String lastModified;
        private final String url;
        private final String name;
        private final List<Integer> zuluVersion;
        private final List<Integer> jdkVersion;
        private final Long size;
        private final String md5Hash;
        private final String sha256Hash;
        private final Integer releaseStatus;
        private final List<String> features;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        BundleDescriptionDetail(
                @JsonProperty("id") Integer id,
                @JsonProperty("arch") String arch,
                @JsonProperty("hw_bitness") String hwBitness,
                @JsonProperty("os") String os,
                @JsonProperty("ext") String ext,
                @JsonProperty("bundle_type") String bundleType,
                @JsonProperty("last_modified") String lastModified,
                @JsonProperty("url") String url,
                @JsonProperty("name") String name,
                @JsonProperty("zulu_version") List<Integer> zuluVersion,
                @JsonProperty("jdk_version") List<Integer> jdkVersion,
                @JsonProperty("size") Long size,
                @JsonProperty("md5_hash") String md5Hash,
                @JsonProperty("sha256_hash") String sha256Hash,
                @JsonProperty("release_status") Integer releaseStatus,
                @JsonProperty("features") List<String> features) {
            this.id = id;
            this.arch = arch;
            this.hwBitness = hwBitness;
            this.os = os;
            this.ext = ext;
            this.bundleType = bundleType;
            this.lastModified = lastModified;
            this.url = url;
            this.name = name;
            this.zuluVersion = zuluVersion;
            this.jdkVersion = jdkVersion;
            this.size = size;
            this.md5Hash = md5Hash;
            this.sha256Hash = sha256Hash;
            this.releaseStatus = releaseStatus;
            this.features = features;
        }

        @SuppressWarnings("unused")
        public Integer getId() {
            return id;
        }

        @SuppressWarnings("unused")
        public String getArch() {
            return arch;
        }

        @SuppressWarnings("unused")
        public String getHwBitness() {
            return hwBitness;
        }

        @SuppressWarnings("unused")
        public String getOs() {
            return os;
        }

        @SuppressWarnings("unused")
        public String getExt() {
            return ext;
        }

        @SuppressWarnings("unused")
        public String getBundleType() {
            return bundleType;
        }

        @SuppressWarnings("unused")
        public String getLastModified() {
            return lastModified;
        }

        @SuppressWarnings("unused")
        public String getUrl() {
            return url;
        }

        @SuppressWarnings("unused")
        public String getName() {
            return name;
        }

        @SuppressWarnings("unused")
        public List<Integer> getZuluVersion() {
            return zuluVersion;
        }

        @SuppressWarnings("unused")
        public List<Integer> getJdkVersion() {
            return jdkVersion;
        }

        @SuppressWarnings("unused")
        public Long getSize() {
            return size;
        }

        @SuppressWarnings("unused")
        public String getMd5Hash() {
            return md5Hash;
        }

        @SuppressWarnings("unused")
        public String getSha256Hash() {
            return sha256Hash;
        }

        @SuppressWarnings("unchecked")
        public Integer getReleaseStatus() {
            return releaseStatus;
        }

        @SuppressWarnings("unused")
        public List<String> getFeatures() {
            return features;
        }
    }

    public Map<String, JavaVersion> getVersions() {
        var client = HttpClient.newHttpClient();
        var majorVersions = request(client, Endpoint.LIST, new TypeReference<List<BundleDescription>>() {
        })
                .stream()
                .map(bundleDescription -> bundleDescription.jdkVersion.get(0))
                .distinct()
                .collect(Collectors.toList());

        var urls = majorVersions
                .stream()
                .map(majorVersion -> Map.entry(String.format("%s-%s", "zulu", majorVersion), String.format("%slatest/?jdk_version=%s", Endpoint.LIST, majorVersion)))
                .flatMap(entry ->
                        Stream.of(Bundle.values()).map(bundle -> Map.entry(String.format("%s-%s", entry.getKey(), bundle.name), String.format("%s&bundle_type=%s", entry.getValue(), bundle.name)))
                )
                .map(entry -> Map.entry(String.format("%s-%s", entry.getKey(), "hotspot"), entry.getValue()))
                .flatMap(entry ->
                        Stream.of(Arch.values()).flatMap(
                                arch -> Stream.of(HwBitness.values()).map(
                                        bitness -> {
                                            String architecture = arch(arch.name, bitness.bitness);
                                            return Map.entry(
                                                    String.format("%s-%s", entry.getKey(), architecture), String.format("%s&arch=%s&hw_bitness=%s", entry.getValue(), arch.name, bitness.bitness));
                                        }
                                )
                        )
                )
                .flatMap(entry ->
                        Stream.of(true, false)
                                .map(
                                        fx -> fx ? Map.entry(
                                                String.format("%s-%s", entry.getKey(), "fx"), String.format("%s&features=fx", entry.getValue())) : entry
                                )
                )
                .flatMap(entry ->
                        Stream.of(Ext.values())
                                .map(
                                        ext -> Map.entry(
                                                String.format("%s-%s", entry.getKey(), (ext.ext.equals("archive") ? "archive" : ext.ext)), String.format("%s&ext=%s", entry.getValue(), ext.ext))
                                )
                )
                .flatMap(entry ->
                        Stream.of(OS.values())
                                .map(
                                        os -> Map.entry(String.format("%s-%s", entry.getKey(), os.name), String.format("%s&os=%s", entry.getValue(), os.name))
                                )
                )
                .filter(entry -> Stream.of(Whitelist.values()).anyMatch(e -> entry.getKey().endsWith(e.pattern)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        var versions = urls.entrySet()
                .stream()
                .map(entry -> {
                    Optional<Map.Entry<String, BundleDescriptionDetail>> ent;
                    try {
                        ent = Optional.of(Map.entry(entry.getKey(), request(client, entry.getValue(), BundleDescriptionDetail.class)));
                    } catch (UncheckedIOException e) {
                        ent = Optional.empty();
                    }
                    return ent;
                })
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return versions.entrySet().stream().map(entry ->
                {
                    String architecture = arch(entry.getValue().arch, entry.getValue().hwBitness);
                    return Map.entry(entry.getKey(),
                            new JavaVersion(
                                    entry.getKey(),
                                    "zulu",
                                    "zulu",
                                    entry.getValue().jdkVersion.get(0),
                                    architecture,
                                    entry.getValue().jdkVersion.stream().map(Object::toString).collect(Collectors.joining(".")),
                                    Ext.fromExt(entry.getValue().ext).orElseThrow(RuntimeException::new).type,
                                    Ext.fromExt(entry.getValue().ext).orElseThrow(RuntimeException::new).type.equals("archive") ? null : entry.getValue().ext,
                                    entry.getValue().url,
                                    "hotspot",
                                    entry.getValue().os,
                                    entry.getValue().bundleType,
                                    entry.getValue().features.contains("fx"),
                                    entry.getValue().lastModified
                            ));
                }
        )
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    }

    private static String arch(String arch, String bitness) {
        String architecture;
        if (arch.equals("x86") && bitness.equals(HwBitness.Bit64.bitness)) {
            architecture = "x64";
        } else if (arch.equals("arm")) {
            architecture = bitness.equals(HwBitness.Bit64.bitness) ? "aarch64" : "arm32";
        } else {
            architecture = arch;
        }
        return architecture;
    }
}
