package com.github.poad.openjdk.finder.backend.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.github.poad.openjdk.finder.backend.entity.JavaVersion;
import lombok.Getter;
import lombok.Value;

import java.net.http.HttpClient;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LibericaJdkV1ApiClient extends JsonHttpClient implements OpenJdkApiClient {
    private static class Endpoint {
        private static final String RELEASE_VERSIONS = "https://api.bell-sw.com/v1/liberica/releases";
    }

    @Value
    @Getter
    public static class Release {
        String filename;
        String downloadUrl;
        Integer size;
        String sha1;
        String version;
        String os;
        String bitness;
        Integer featureVersion;
        Integer interimVersion;
        Integer updateVersion;
        Integer patchVersion;
        Integer buildVersion;
        Boolean latestInFeatureVersion;
        Boolean latestLts;
        Boolean latest;
        Boolean lts;
        Boolean ga;
        Boolean fx;
        Boolean eol;
        String architecture;
        String installationType;
        String packageType;
        String bundleType;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        public Release(
                @JsonProperty("filename") String filename,
                @JsonProperty("downloadUrl") String downloadUrl,
                @JsonProperty("size") Integer size,
                @JsonProperty("sha1") String sha1,
                @JsonProperty("version") String version,
                @JsonProperty("os") String os,
                @JsonProperty("bitness") String bitness,
                @JsonProperty("featureVersion") Integer featureVersion,
                @JsonProperty("interimVersion") Integer interimVersion,
                @JsonProperty("updateVersion") Integer updateVersion,
                @JsonProperty("patchVersion") Integer patchVersion,
                @JsonProperty("buildVersion") Integer buildVersion,
                @JsonProperty("latestInFeatureVersion") Boolean latestInFeatureVersion,
                @JsonProperty("latestLTS") Boolean latestLts,
                @JsonProperty("latest") Boolean latest,
                @JsonProperty("LTS") Boolean lts,
                @JsonProperty("GA") Boolean ga,
                @JsonProperty("FX") Boolean fx,
                @JsonProperty("EOL") Boolean eol,
                @JsonProperty("architecture") String architecture,
                @JsonProperty("installationType") String installationType,
                @JsonProperty("packageType") String packageType,
                @JsonProperty("bundleType") String bundleType) {
            this.filename = filename;
            this.downloadUrl = downloadUrl;
            this.size = size;
            this.sha1 = sha1;
            this.version = version;
            this.os = os;
            this.bitness = bitness;
            this.featureVersion = featureVersion;
            this.interimVersion = interimVersion;
            this.updateVersion = updateVersion;
            this.patchVersion = patchVersion;
            this.buildVersion = buildVersion;
            this.latestInFeatureVersion = latestInFeatureVersion;
            this.latestLts = latestLts;
            this.latest = latest;
            this.lts = lts;
            this.ga = ga;
            this.fx = fx;
            this.eol = eol;
            this.architecture = architecture;
            this.installationType = installationType;
            this.packageType = packageType;
            this.bundleType = bundleType;
        }
    }

    public Map<String, JavaVersion> getVersions() {
        var client = HttpClient.newHttpClient();
        var releases = request(client, Endpoint.RELEASE_VERSIONS, new TypeReference<List<Release>>() {
        });
        return releases.stream()
                .filter(release -> release.ga)
                .filter(release -> !release.bundleType.equals("jdk-lite"))
                .filter(release -> !release.bundleType.equals("jre-lite"))
                .map(release -> {
                    var bundle = release.bundleType.equals("jdk-full") ? "jdk" : release.bundleType.equals("jre-full") ? "jre" : release.bundleType;
                    String architecture;
                    switch (release.architecture) {
                        case "arm":
                            if (release.bitness.equals("32")) {
                                architecture = "arm32";
                            } else {
                                architecture = "aarch64";
                            }
                            break;
                        case "ppc":
                            architecture = "ppc64le";
                            break;
                        case "sparc":
                            architecture = "sparcv9";
                            break;
                        case "x86":
                            if (release.bitness.equals("32")) {
                                architecture = release.architecture;
                            } else {
                                architecture = "x64";
                            }
                            break;
                        default:
                            architecture = release.architecture;
                            break;
                    }
                    return new JavaVersion(
                            id(release, architecture, bundle),
                            "liberica",
                            "liberica",
                            release.featureVersion,
                            architecture,
                            release.version,
                            release.installationType,
                            release.installationType.equals("installer") ? release.packageType : null,
                            release.downloadUrl,
                            "hotspot",
                            release.os,
                            bundle,
                            release.fx,
                            "sha1",
                            release.sha1,
                            null,
                            null
                    );
                })
                .collect(Collectors.groupingBy(JavaVersion::getId))
                .values()
                .stream()
                .map(v -> v.get(0))
                .collect(Collectors.toMap(JavaVersion::getId, version -> version));

    }

    private static String id(Release release, String architecture, String bundle) {
        return release.fx ? String.format("%s-%d-%s-%s-%s-%s-%s-%s", "liberica", release.featureVersion, bundle, "hotspot", architecture, "fx", release.packageType, release.os) :
                String.format("%s-%d-%s-%s-%s-%s-%s", "liberica", release.featureVersion, bundle, "hotspot", architecture, release.packageType, release.os);
    }
}