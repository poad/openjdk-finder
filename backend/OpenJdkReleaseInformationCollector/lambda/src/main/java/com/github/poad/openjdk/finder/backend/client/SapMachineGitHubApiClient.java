package com.github.poad.openjdk.finder.backend.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.github.poad.openjdk.finder.backend.entity.JavaVersion;

import java.net.http.HttpClient;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SapMachineGitHubApiClient extends JsonHttpClient implements OpenJdkApiClient {

    private static class Endpoint {
        private static final String RELEASE = "https://api.github.com/repos/SAP/SapMachine/releases";
    }

    @SuppressWarnings("unused")
    private enum Arch {
        PPC("ppc", "ppc64"),
        X86("x86", "x64");

        private final String name;
        private final String pkgName;

        Arch(String name, String pkgName) {
            this.name = name;
            this.pkgName = pkgName;
        }
    }

    @SuppressWarnings("unused")
    private enum OS {
        LINUX("linux", "linux", List.of(Arch.X86, Arch.PPC)),
        MACOS("macos", "osx", List.of(Arch.X86)),
        WINDOWS("windows", "windows", List.of(Arch.X86));

        private final String name;
        private final String pkgName;
        private final List<Arch> arches;

        OS(String name, String pkgName, List<Arch> arches) {
            this.name = name;
            this.pkgName = pkgName;
            this.arches = arches;
        }
    }

    private enum Ext {
        RPM("rpm", "installer", List.of(OS.LINUX)),
        MSI("msi", "installer", List.of(OS.WINDOWS)),
        DMG("dmg", "installer", List.of(OS.MACOS)),
        TARBALL("tar.gz", "archive", List.of(OS.LINUX, OS.MACOS)),
        ZIP("zip", "archive", List.of(OS.LINUX));

        private final String ext;
        private final String type;
        private final List<OS> os;

        Ext(String ext, String type, List<OS> os) {
            this.ext = ext;
            this.type = type;
            this.os = os;
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

    @SuppressWarnings("unused")
    static class Person {
        private final String login;
        private final Long id;
        private final String nodeId;
        private final String avatarUrl;
        private final String gravatarId;
        private final String url;
        private final String htmlUrl;
        private final String followersUrl;
        private final String followingUrl;
        private final String gistsUrl;
        private final String starredUrl;
        private final String subscriptionsUrl;
        private final String organizationsUrl;
        private final String reposUrl;
        private final String eventsUrl;
        private final String receivedEventsUrl;
        private final String type;
        private final Boolean siteAdmin;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        Person(
                @JsonProperty("login") String login,
                @JsonProperty("id") Long id,
                @JsonProperty("node_id") String nodeId,
                @JsonProperty("avatar_url") String avatarUrl,
                @JsonProperty("gravatar_id") String gravatarId,
                @JsonProperty("url") String url,
                @JsonProperty("html_url") String htmlUrl,
                @JsonProperty("followers_url") String followersUrl,
                @JsonProperty("following_url") String followingUrl,
                @JsonProperty("gists_url") String gistsUrl,
                @JsonProperty("starred_url") String starredUrl,
                @JsonProperty("subscriptions_url") String subscriptionsUrl,
                @JsonProperty("organizations_url") String organizationsUrl,
                @JsonProperty("repos_url") String reposUrl,
                @JsonProperty("events_url") String eventsUrl,
                @JsonProperty("received_events_url") String receivedEventsUrl,
                @JsonProperty("type") String type,
                @JsonProperty("site_admin") Boolean siteAdmin) {
            this.login = login;
            this.id = id;
            this.nodeId = nodeId;
            this.avatarUrl = avatarUrl;
            this.gravatarId = gravatarId;
            this.url = url;
            this.htmlUrl = htmlUrl;
            this.followersUrl = followersUrl;
            this.followingUrl = followingUrl;
            this.gistsUrl = gistsUrl;
            this.starredUrl = starredUrl;
            this.subscriptionsUrl = subscriptionsUrl;
            this.organizationsUrl = organizationsUrl;
            this.reposUrl = reposUrl;
            this.eventsUrl = eventsUrl;
            this.receivedEventsUrl = receivedEventsUrl;
            this.type = type;
            this.siteAdmin = siteAdmin;
        }

        public String getLogin() {
            return login;
        }

        public Long getId() {
            return id;
        }

        public String getNodeId() {
            return nodeId;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public String getGravatarId() {
            return gravatarId;
        }

        public String getUrl() {
            return url;
        }

        public String getHtmlUrl() {
            return htmlUrl;
        }

        public String getFollowersUrl() {
            return followersUrl;
        }

        public String getFollowingUrl() {
            return followingUrl;
        }

        public String getGistsUrl() {
            return gistsUrl;
        }

        public String getStarredUrl() {
            return starredUrl;
        }

        public String getSubscriptionsUrl() {
            return subscriptionsUrl;
        }

        public String getOrganizationsUrl() {
            return organizationsUrl;
        }

        public String getReposUrl() {
            return reposUrl;
        }

        public String getEventsUrl() {
            return eventsUrl;
        }

        public String getReceivedEventsUrl() {
            return receivedEventsUrl;
        }

        public String getType() {
            return type;
        }

        public Boolean getSiteAdmin() {
            return siteAdmin;
        }
    }

    @SuppressWarnings("unused")
    static class Release {
        private final String url;
        private final String htmlUrl;
        private final String assetsUrl;
        private final String uploadUrl;
        private final String tarballUrl;
        private final String zipballUrl;
        private final Long id;
        private final String nodeId;
        private final String tagName;
        private final String targetCommitish;
        private final String name;
        private final String body;
        private final Boolean draft;
        private final Boolean prerelease;
        private final String createdAt;
        private final String publishedAt;
        private final Person author;
        private final List<Asset> assets;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        Release(
                @JsonProperty("url") String url,
                @JsonProperty("html_url") String htmlUrl,
                @JsonProperty("assets_url") String assetsUrl,
                @JsonProperty("upload_url") String uploadUrl,
                @JsonProperty("tarball_url") String tarballUrl,
                @JsonProperty("zipball_url") String zipballUrl,
                @JsonProperty("id") Long id,
                @JsonProperty("node_id") String nodeId,
                @JsonProperty("tag_name") String tagName,
                @JsonProperty("target_commitish") String targetCommitish,
                @JsonProperty("name") String name,
                @JsonProperty("body") String body,
                @JsonProperty("draft") Boolean draft,
                @JsonProperty("prerelease") Boolean prerelease,
                @JsonProperty("created_at") String createdAt,
                @JsonProperty("published_at") String publishedAt,
                @JsonProperty("author") Person author,
                @JsonProperty("assets")List<Asset> assets) {
            this.url = url;
            this.htmlUrl = htmlUrl;
            this.assetsUrl = assetsUrl;
            this.uploadUrl = uploadUrl;
            this.tarballUrl = tarballUrl;
            this.zipballUrl = zipballUrl;
            this.id = id;
            this.nodeId = nodeId;
            this.tagName = tagName;
            this.targetCommitish = targetCommitish;
            this.name = name;
            this.body = body;
            this.draft = draft;
            this.prerelease = prerelease;
            this.createdAt = createdAt;
            this.publishedAt = publishedAt;
            this.author = author;
            this.assets = assets;
        }

        public String getUrl() {
            return url;
        }

        public String getHtmlUrl() {
            return htmlUrl;
        }

        public String getAssetsUrl() {
            return assetsUrl;
        }

        public String getUploadUrl() {
            return uploadUrl;
        }

        public String getTarballUrl() {
            return tarballUrl;
        }

        public String getZipballUrl() {
            return zipballUrl;
        }

        public Long getId() {
            return id;
        }

        public String getNodeId() {
            return nodeId;
        }

        public String getTagName() {
            return tagName;
        }

        public String getTargetCommitish() {
            return targetCommitish;
        }

        public String getName() {
            return name;
        }

        public String getBody() {
            return body;
        }

        public Boolean getDraft() {
            return draft;
        }

        public Boolean getPrerelease() {
            return prerelease;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public Person getAuthor() {
            return author;
        }

        public List<Asset> getAssets() {
            return assets;
        }

        static class Asset {
            private final String url;
            private final String browserDownloadUrl;
            private final Long id;
            private final String nodeId;
            private final String name;
            private final String label;
            private final String state;
            private final String contentType;
            private final Long size;
            private final Long downloadCount;
            private final String createdAt;
            private final String updatedAt;
            private final Person uploader;

            @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
            Asset(
                    @JsonProperty("url") String url,
                    @JsonProperty("browser_download_url") String browserDownloadUrl,
                    @JsonProperty("id") Long id,
                    @JsonProperty("node_id") String nodeId,
                    @JsonProperty("name") String name,
                    @JsonProperty("label") String label,
                    @JsonProperty("state") String state,
                    @JsonProperty("content_type") String contentType,
                    @JsonProperty("size") Long size,
                    @JsonProperty("download_count") Long downloadCount,
                    @JsonProperty("created_at") String createdAt,
                    @JsonProperty("updated_at") String updatedAt,
                    @JsonProperty("uploader") Person uploader) {
                this.url = url;
                this.browserDownloadUrl = browserDownloadUrl;
                this.id = id;
                this.nodeId = nodeId;
                this.name = name;
                this.label = label;
                this.state = state;
                this.contentType = contentType;
                this.size = size;
                this.downloadCount = downloadCount;
                this.createdAt = createdAt;
                this.updatedAt = updatedAt;
                this.uploader = uploader;
            }

            public String getUrl() {
                return url;
            }

            public String getBrowserDownloadUrl() {
                return browserDownloadUrl;
            }

            public Long getId() {
                return id;
            }

            public String getNodeId() {
                return nodeId;
            }

            public String getName() {
                return name;
            }

            public String getLabel() {
                return label;
            }

            public String getState() {
                return state;
            }

            public String getContentType() {
                return contentType;
            }

            public Long getSize() {
                return size;
            }

            public Long getDownloadCount() {
                return downloadCount;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public Person getUploader() {
                return uploader;
            }
        }
    }

    private static class Key {
        private final Bundle bundle;
        private final Arch arch;
        private final OS os;
        private final Ext packageType;


        private Key(Bundle bundle, Arch arch, OS os, Ext packageType) {
            this.bundle = bundle;
            this.arch = arch;
            this.os = os;
            this.packageType = packageType;
        }

        @Override
        public String toString() {
            return String.format("%s-hotspot-%s-%s-%s", bundle.name, arch.pkgName, packageType.type, os.pkgName);
        }
    }

    private static final Map<Key, String> FILENAME_PATTERNS = Stream.of(Ext.values())
            .flatMap(ext -> ext.os.stream()
                    .flatMap(os -> os.arches.stream()
                            .flatMap(arch -> Stream.of(Bundle.values())
                                    .map(bundle -> Map.entry(
                                            new Key(bundle, arch, os, ext),
                                            String.format("sapmachine-%s-%%s_%s-%s_bin.%s", bundle.name, os.name, arch.pkgName, ext.ext))
                                    )
                            )
                    )
            )
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    @Override
    public Map<String, JavaVersion> getVersions() {
        var client = HttpClient.newHttpClient();
        var releases = request(client, Endpoint.RELEASE, new TypeReference<List<Release>>() {})
                .stream()
                .filter(release -> !release.draft)
                .filter(release -> !release.prerelease)
                .map(release -> {
                    var version = release.name.replace("sapmachine-","");
                    return Map.entry(version, release);
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return releases.entrySet()
                .stream()
                .flatMap(entry -> entry.getValue().assets.stream()
                        .filter(asset -> FILENAME_PATTERNS.values().stream()
                                .map(s -> String.format(s, entry.getKey()))
                                .collect(Collectors.toList()).contains(asset.name))
                        .flatMap(asset -> FILENAME_PATTERNS.keySet().stream()
                                    .map(s -> {
                                        var version = entry.getKey();
                                        var majorVersion = version.contains(".") ? version.substring(0, version.indexOf(".")) : version;

                                        var ext = Stream.of(Ext.values())
                                                .filter(e -> asset.name.endsWith(e.ext))
                                                .findFirst()
                                                .orElseThrow(RuntimeException::new).ext;
                                        var hashUrl = asset.name.replace(ext, "sha256");

                                        var id = String.format("sapmachine-%s-%s", version, String.format(s.toString(), entry.getKey()));
                                        return Map.entry(id, new JavaVersion(
                                                id,
                                                "sapmachine",
                                                "sapmachine",
                                                Integer.parseInt(majorVersion),
                                                s.arch.pkgName,
                                                version,
                                                s.packageType.type,
                                                s.packageType.type.equals("installer") ? s.packageType.ext : null,
                                                asset.browserDownloadUrl,
                                                "hotspot",
                                                s.os.name,
                                                s.bundle.name,
                                                false,
                                                "sha256",
                                                hashUrl,
                                                null,
                                                asset.updatedAt
                                        ));
                                    })
                        )
                    )
                .collect(Collectors.groupingBy(Map.Entry::getKey))
                .values()
                .stream()
                .map(v -> v.get(0))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
