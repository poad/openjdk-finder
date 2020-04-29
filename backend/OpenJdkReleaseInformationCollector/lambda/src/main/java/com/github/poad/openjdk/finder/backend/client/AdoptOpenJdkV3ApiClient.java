package com.github.poad.openjdk.finder.backend.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.github.poad.openjdk.finder.backend.entity.JavaVersion;

import java.net.http.HttpClient;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdoptOpenJdkV3ApiClient extends JsonHttpClient implements OpenJdkApiClient {
    private static class Endpoint {
        private static final String AVAILABLE_RELEASES = "https://api.adoptopenjdk.net/v3/info/available_releases";
        private static final String FEATURE_RELEASE = "https://api.adoptopenjdk.net/v3/assets/feature_releases/%d/ga?heap_size=normal&page=0&page_size=100&sort_order=DESC&vendor=%s";
    }

    public static class AvailableRelease {
        @SuppressWarnings("unused")
        private final List<Integer> availableLtsReleases;
        private final List<Integer> availableReleases;
        @SuppressWarnings("unused")
        private final String mostRecentFeatureRelease;
        @SuppressWarnings("unused")
        private final String mostRecentLts;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        public AvailableRelease(
                @JsonProperty("available_lts_releases") List<Integer> availableLtsReleases,
                @JsonProperty("available_releases") List<Integer> availableReleases,
                @JsonProperty("most_recent_feature_release") String mostRecentFeatureRelease,
                @JsonProperty("most_recent_lts") String mostRecentLts) {
            this.availableLtsReleases = availableLtsReleases;
            this.availableReleases = availableReleases;
            this.mostRecentFeatureRelease = mostRecentFeatureRelease;
            this.mostRecentLts = mostRecentLts;
        }
    }

    public static class ReleaseVersion {
        @SuppressWarnings("unused")
        private final Integer adoptBuildNumber;
        @SuppressWarnings("unused")
        private final Integer build;
        private final Integer major;
        @SuppressWarnings("unused")
        private final Integer minor;
        @SuppressWarnings("unused")
        private final String openjdkVersion;
        @SuppressWarnings("unused")
        private final String optional;
        @SuppressWarnings("unused")
        private final String pre;
        @SuppressWarnings("unused")
        private final Integer security;
        private final String semver;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        public ReleaseVersion(
                @JsonProperty("adopt_build_number") Integer adoptBuildNumber,
                @JsonProperty("build") Integer build,
                @JsonProperty("major") Integer major,
                @JsonProperty("minor") Integer minor,
                @JsonProperty("openjdk_version") String openjdkVersion,
                @JsonProperty("optional") String optional,
                @JsonProperty("pre") String pre,
                @JsonProperty("security") Integer security,
                @JsonProperty("semver") String semver) {
            this.adoptBuildNumber = adoptBuildNumber;
            this.build = build;
            this.major = major;
            this.minor = minor;
            this.openjdkVersion = openjdkVersion;
            this.optional = optional;
            this.pre = pre;
            this.security = security;
            this.semver = semver;
        }
    }

    public static class Versions {
        private final List<ReleaseVersion> versions;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        public Versions(@JsonProperty("versions") List<ReleaseVersion> versions) {
            this.versions = versions;
        }
    }

    public static class Package {
        @SuppressWarnings("unused")
        private final String checksum;
        @SuppressWarnings("unused")
        private final String checksumLink;
        @SuppressWarnings("unused")
        private final Integer downloadCount;
        private final String link;
        @SuppressWarnings("unused")
        private final String name;
        @SuppressWarnings("unused")
        private final String signatureLink;
        @SuppressWarnings("unused")
        private final Integer size;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        public Package(
                @JsonProperty("checksum") String checksum,
                @JsonProperty("checksum_link") String checksumLink,
                @JsonProperty("download_count") Integer downloadCount,
                @JsonProperty("link") String link,
                @JsonProperty("name") String name,
                @JsonProperty("signature_link") String signatureLink,
                @JsonProperty("size") Integer size) {
            this.checksum = checksum;
            this.checksumLink = checksumLink;
            this.downloadCount = downloadCount;
            this.link = link;
            this.name = name;
            this.signatureLink = signatureLink;
            this.size = size;
        }
    }

    public static class Installer {
        @SuppressWarnings("unused")
        private final String checksum;
        @SuppressWarnings("unused")
        private final String checksumLink;
        @SuppressWarnings("unused")
        private final Integer downloadCount;
        @SuppressWarnings("unused")
        private final String link;
        @SuppressWarnings("unused")
        private final String name;
        @SuppressWarnings("unused")
        private final String signatureLink;
        @SuppressWarnings("unused")
        private final Integer size;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        public Installer(
                @JsonProperty("checksum") String checksum,
                @JsonProperty("checksum_link") String checksumLink,
                @JsonProperty("download_count") Integer downloadCount,
                @JsonProperty("link") String link,
                @JsonProperty("name") String name,
                @JsonProperty("signature_link") String signatureLink,
                @JsonProperty("size") Integer size) {
            this.checksum = checksum;
            this.checksumLink = checksumLink;
            this.downloadCount = downloadCount;
            this.link = link;
            this.name = name;
            this.signatureLink = signatureLink;
            this.size = size;
        }
    }

    public static class Source {
        @SuppressWarnings("unused")
        private final String link;
        @SuppressWarnings("unused")
        private final String name;
        @SuppressWarnings("unused")
        private final Integer size;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        public Source(
                @JsonProperty("link") String link,
                @JsonProperty("name") String name,
                @JsonProperty("size") Integer size) {
            this.link = link;
            this.name = name;
            this.size = size;
        }
    }

    public static class Binary {
        private final String architecture;
        @SuppressWarnings("unused")
        private final Long downloadCount;
        @SuppressWarnings("unused")
        private final String heapSize;
        private final String imageType;
        @SuppressWarnings("unused")
        private final Installer installer;
        private final String jvmImpl;
        private final String os;
        private final Package pkg;
        @SuppressWarnings("unused")
        private final String project;
        @SuppressWarnings("unused")
        private final String scmRef;
        @SuppressWarnings("unused")
        private final String updatedAt;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        public Binary(
                @JsonProperty("architecture") String architecture,
                @JsonProperty("download_count") Long downloadCount,
                @JsonProperty("heap_size") String heapSize,
                @JsonProperty("image_type") String imageType,
                @JsonProperty("installer") Installer installer,
                @JsonProperty("jvm_impl") String jvmImpl,
                @JsonProperty("os") String os,
                @JsonProperty("package") Package pkg,
                @JsonProperty("project") String project,
                @JsonProperty("scm_ref") String scmRef,
                @JsonProperty("updated_at") String updatedAt) {
            this.architecture = architecture;
            this.downloadCount = downloadCount;
            this.heapSize = heapSize;
            this.imageType = imageType;
            this.installer = installer;
            this.jvmImpl = jvmImpl;
            this.os = os;
            this.pkg = pkg;
            this.project = project;
            this.scmRef = scmRef;
            this.updatedAt = updatedAt;
        }
    }

    public static class Assets {
        private final List<Binary> binaries;
        @SuppressWarnings("unused")
        private final Long downloadCount;
        @SuppressWarnings("unused")
        private final String id;
        @SuppressWarnings("unused")
        private final String releaseLink;
        @SuppressWarnings("unused")
        private final String releaseName;
        @SuppressWarnings("unused")
        private final String releaseType;
        @SuppressWarnings("unused")
        private final String timestamp;
        @SuppressWarnings("unused")
        private final String updatedAt;
        @SuppressWarnings("unused")
        private final String vendor;
        @SuppressWarnings("unused")
        private final Source source;
        @SuppressWarnings("unused")
        private final ReleaseVersion versionData;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        public Assets(
                @JsonProperty("binaries") List<Binary> binaries,
                @JsonProperty("download_count") Long downloadCount,
                @JsonProperty("id") String id,
                @JsonProperty("release_link") String releaseLink,
                @JsonProperty("release_name") String releaseName,
                @JsonProperty("release_type") String releaseType,
                @JsonProperty("timestamp") String timestamp,
                @JsonProperty("updated_at") String updatedAt,
                @JsonProperty("vendor") String vendor,
                @JsonProperty("source") Source source,
                @JsonProperty("version_data") ReleaseVersion versionData) {
            this.binaries = binaries;
            this.downloadCount = downloadCount;
            this.id = id;
            this.releaseLink = releaseLink;
            this.releaseName = releaseName;
            this.releaseType = releaseType;
            this.timestamp = timestamp;
            this.updatedAt = updatedAt;
            this.vendor = vendor;
            this.source = source;
            this.versionData = versionData;
        }
    }

    private enum Vendor {
        ADOPT_OPENJDK("adoptopenjdk"),
        OPENJDK("openjdk");

        private final String vendor;

        Vendor(String vendor) {
            this.vendor = vendor;
        }

        @Override
        public String toString() {
            return this.vendor;
        }
    }

    private static class Params {
        private final int majorVersion;
        private final String vendor;

        private Params(int majorVersion, String vendor) {
            this.majorVersion = majorVersion;
            this.vendor = vendor;
        }
    }

    private static class Key implements Comparable<Key> {
        private final String os;
        private final ReleaseVersion version;
        private final String vendor;
        private final String releaseName;

        private Key(ReleaseVersion version, String os, String vendor, String releaseName) {
            this.os = os;
            this.version = version;
            this.vendor = vendor;
            this.releaseName = releaseName;
        }

        @Override
        public String toString() {
            return String.format("%s-%s-%s-%s", version.semver, os, vendor, releaseName);
        }

        @Override
        public int compareTo(Key o) {
            return 0;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return os.equals(key.os) &&
                    version.equals(key.version) &&
                    vendor.equals(key.vendor) &&
                    releaseName.equals(key.releaseName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(os, version, vendor, releaseName);
        }
    }

    public Map<String, JavaVersion> getVersions() {
        var client = HttpClient.newHttpClient();
        var releases = request(client, Endpoint.AVAILABLE_RELEASES, AvailableRelease.class);
        var majorVersions = releases.availableReleases;
        var params = majorVersions
                .stream()
                .flatMap(majorVersion -> Stream.of(Vendor.values())
                        .map(vendor -> new Params(majorVersion, vendor.vendor)))
                .collect(Collectors.toList());
        return params
                .stream()
                .flatMap(param ->
                    binaries(client, param.majorVersion, param.vendor, binary -> !binary.imageType.equals("testimage") && !binary.imageType.equals("debugimage"))
                            .flatMap(entry -> {
                                var binary = entry.getValue();
                                var version = entry.getKey();
                                var releaseVersion = version.releaseName.replace("jdk-", "");
                                var os = binary.os.equals("mac") ? "macos" : binary.os;
                                String architecture;
                                switch (binary.architecture) {
                                    case "x32":
                                        architecture = "x86";
                                        break;
                                    case "arm":
                                        architecture = "arm32";
                                        break;
                                    default:
                                        architecture = binary.architecture;
                                }
                                var archive = createEntity(version.vendor, version.version, releaseVersion, binary, architecture, os, InstallerType.ARCHIVE, null);
                                if (Objects.nonNull(binary.installer)) {
                                    var extension = binary.installer.name.substring(binary.installer.name.lastIndexOf(".") + 1);
                                    var installer = createEntity(version.vendor, version.version, releaseVersion, binary, architecture, os, InstallerType.INSTALLER, extension);

                                    return Stream.of(archive, installer);
                                }
                                return Stream.of(archive);
                            })
                            .collect(Collectors.groupingBy(JavaVersion::getId))
                            .entrySet()
                            .stream()
                            .map(entries -> Map.entry(entries.getKey(), entries.getValue().get(0)))
                )
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Stream<Map.Entry<Key, Binary>> binaries(HttpClient client, int majorVersion, String vendor, Predicate<Binary> predicate) {
        var url = String.format(Endpoint.FEATURE_RELEASE, majorVersion, vendor);
        try {
            return request(client, url, new TypeReference<List<Assets>>() {
            })
                    .stream()
                    .flatMap(asset ->
                            asset.binaries
                                    .stream()
                                    .filter(predicate)
                                    .map(binary -> Map.entry(new Key(asset.versionData, binary.os, asset.vendor, asset.releaseName), binary))
                    );
        } catch (RuntimeException e) {
//            throw new RuntimeException(url, e);
            return Stream.empty();
        }
    }

    private JavaVersion createEntity(
            String vendor,
            ReleaseVersion version,
            String releaseVersion,
            Binary binary,
            String architecture,
            String os,
            InstallerType installerType,
            String extension) {
        var id = installerType.equals(InstallerType.INSTALLER) ?
                String.format("%s-%d-%s-%s-%s-%s-%s",
                        vendor,
                        version.major,
                        binary.jvmImpl,
                        binary.imageType,
                        architecture,
                        extension,
                        os)
                :
                String.format("%s-%d-%s-%s-%s-%s",
                        vendor,
                        version.major,
                        binary.jvmImpl,
                        binary.imageType,
                        architecture,
                        os);


        return new JavaVersion(
                id,
                vendor,
                "adoptopenjdk",
                version.major,
                architecture,
                releaseVersion,
                installerType.toString(),
                extension,
                binary.pkg.link,
                binary.jvmImpl,
                os,
                binary.imageType,
                false,
                "sha256",
                binary.pkg.checksum,
                binary.pkg.signatureLink,
                binary.updatedAt);
    }
}
