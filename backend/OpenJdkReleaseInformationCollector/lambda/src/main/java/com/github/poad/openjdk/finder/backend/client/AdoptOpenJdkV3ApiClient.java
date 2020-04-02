package com.github.poad.openjdk.finder.backend.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.poad.openjdk.finder.backend.entity.JavaVersion;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AdoptOpenJdkV3ApiClient {
    private static class Endpoint {
        private static final String AVAILABLE_RELEASES = "https://api.adoptopenjdk.net/v3/info/available_releases";
        private static final String RELEASE_VERSIONS = "https://api.adoptopenjdk.net/v3/info/release_versions?page=0&page_size=1000&release_type=ga&sort_order=DESC&vendor=%s";
        private static final String VERSION = "https://api.adoptopenjdk.net/v3/assets/version/%s?heap_size=normal&page=0&page_size=100&project=jdk&release_type=ga&sort_order=DESC&vendor=%s";
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


    private final ObjectMapper mapper = new ObjectMapper();

    public Map<String, JavaVersion> getVersions() {
        var client = HttpClient.newHttpClient();
        var releases = request(client, Endpoint.AVAILABLE_RELEASES, AvailableRelease.class);
        return releases.availableReleases.stream().flatMap(majorVersion ->
                Arrays.stream(Vendor.values())
                        .map(Vendor::toString)
                        .flatMap(vendor -> {
                            var versions = request(client, String.format(Endpoint.RELEASE_VERSIONS, vendor), Versions.class)
                                    .versions
                                    .stream()
                                    .map(v -> Map.entry(v.major, v))
                                    .collect(Collectors.groupingBy(Map.Entry::getKey))
                                    .values()
                                    .stream()
                                    .map(entries -> entries.get(0))
                                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                            return versions.values().stream().flatMap(version ->
                                    request(client, String.format(Endpoint.VERSION, version.semver, vendor), new TypeReference<List<Assets>>() {
                                    })
                                            .stream()
                                            .flatMap(binaries -> binaries.binaries.stream())
                                            .map(binary -> {
                                                var id = String.format("%s-%d-%s-%s-%s-%s", vendor, version.major, binary.jvmImpl, binary.imageType, binary.architecture, binary.os);
                                                return new JavaVersion(id, vendor, version.major, binary.architecture, version.semver, binary.pkg.link, binary.imageType, binary.jvmImpl, binary.os, binary.updatedAt);
                                            })
                            );
                        })
        ).collect(Collectors.groupingBy(JavaVersion::getId))
                .values()
                .stream()
                .map(entries -> entries.get(0))
                .collect(Collectors.toMap(JavaVersion::getId, javaVersion -> javaVersion));
    }

    private <T> T request(HttpClient client, String url, Class<T> type) {
        try {
            var response = request(client, url);
            return mapper.readValue(response.body(), type);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private <T> T request(HttpClient client, String url, TypeReference<T> type) {
        try {
            var response = request(client, url);
            return mapper.readValue(response.body(), type);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private HttpResponse<String> request(HttpClient client, String url) {
        var request = HttpRequest
                .newBuilder(URI.create(url))
                .GET()
                .build();
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}