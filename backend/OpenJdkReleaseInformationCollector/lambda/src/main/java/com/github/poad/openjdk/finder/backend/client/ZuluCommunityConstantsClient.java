package com.github.poad.openjdk.finder.backend.client;

import com.github.poad.openjdk.finder.backend.client.constants.zulu.Zulu14JdkFxVersion;
import com.github.poad.openjdk.finder.backend.client.constants.zulu.Zulu14JdkVersion;
import com.github.poad.openjdk.finder.backend.client.constants.zulu.Zulu14JreFxVersion;
import com.github.poad.openjdk.finder.backend.client.constants.zulu.Zulu14JreVersion;
import com.github.poad.openjdk.finder.backend.entity.JavaVersion;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ZuluCommunityConstantsClient extends JsonHttpClient implements OpenJdkApiClient {

    public Map<String, JavaVersion> getVersions() {
        return Stream.of(
                Zulu14JdkFxVersion.VERSIONS,
                Zulu14JdkVersion.VERSIONS,
                Zulu14JreFxVersion.VERSIONS,
                Zulu14JreVersion.VERSIONS
        )
                .flatMap(s -> s)
                .map(v -> Map.entry(v.getId(), v)
                )
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
