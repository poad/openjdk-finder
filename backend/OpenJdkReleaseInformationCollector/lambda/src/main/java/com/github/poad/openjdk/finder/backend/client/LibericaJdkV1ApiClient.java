package com.github.poad.openjdk.finder.backend.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.github.poad.openjdk.finder.backend.entity.JavaVersion;

import java.net.http.HttpClient;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LibericaJdkV1ApiClient extends JsonHttpClient implements OpenJdkApiClient {
    private static class Endpoint {
        private static final String RELEASE_VERSIONS = "https://api.bell-sw.com/v1/liberica/releases";
    }

    public static class Release {
        private final String filename;
        private final String downloadUrl;
        private final Integer size;
        private final String sha1;
        private final String version;
        private final String os;
        private final String bitness;
        private final Integer featureVersion;
        private final Integer interimVersion;
        private final Integer updateVersion;
        private final Integer patchVersion;
        private final Integer buildVersion;
        private final Boolean latestInFeatureVersion;
        private final Boolean latestLts;
        private final Boolean latest;
        private final Boolean lts;
        private final Boolean ga;
        private final Boolean fx;
        private final Boolean eol;
        private final String architecture;
        private final String installationType;
        private final String packageType;
        private final String bundleType;

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

        @SuppressWarnings("unused")
        public String getFilename() {
            return filename;
        }

        @SuppressWarnings("unused")
        public String getDownloadUrl() {
            return downloadUrl;
        }

        @SuppressWarnings("unused")
        public Integer getSize() {
            return size;
        }

        @SuppressWarnings("unused")
        public String getSha1() {
            return sha1;
        }

        @SuppressWarnings("unused")
        public String getVersion() {
            return version;
        }

        @SuppressWarnings("unused")
        public String getOs() {
            return os;
        }

        @SuppressWarnings("unused")
        public String getBitness() {
            return bitness;
        }

        @SuppressWarnings("unused")
        public Integer getFeatureVersion() {
            return featureVersion;
        }

        @SuppressWarnings("unused")
        public Integer getInterimVersion() {
            return interimVersion;
        }

        @SuppressWarnings("unused")
        public Integer getUpdateVersion() {
            return updateVersion;
        }

        @SuppressWarnings("unused")
        public Integer getPatchVersion() {
            return patchVersion;
        }

        @SuppressWarnings("unused")
        public Integer getBuildVersion() {
            return buildVersion;
        }

        @SuppressWarnings("unused")
        public Boolean getLatestInFeatureVersion() {
            return latestInFeatureVersion;
        }

        @SuppressWarnings("unused")
        public Boolean getLatestLts() {
            return latestLts;
        }

        @SuppressWarnings("unused")
        public Boolean getLatest() {
            return latest;
        }

        @SuppressWarnings("unused")
        public Boolean getLts() {
            return lts;
        }

        @SuppressWarnings("unused")
        public Boolean getGa() {
            return ga;
        }

        @SuppressWarnings("unused")
        public Boolean getFx() {
            return fx;
        }

        @SuppressWarnings("unused")
        public Boolean getEol() {
            return eol;
        }

        @SuppressWarnings("unused")
        public String getArchitecture() {
            return architecture;
        }

        @SuppressWarnings("unused")
        public String getInstallationType() {
            return installationType;
        }

        @SuppressWarnings("unused")
        public String getPackageType() {
            return packageType;
        }

        @SuppressWarnings("unused")
        public String getBundleType() {
            return bundleType;
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