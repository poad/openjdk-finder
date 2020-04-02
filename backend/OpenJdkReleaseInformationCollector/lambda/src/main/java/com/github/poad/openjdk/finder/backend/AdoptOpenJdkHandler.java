package com.github.poad.openjdk.finder.backend;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.github.poad.openjdk.finder.backend.client.AdoptOpenJdkV3ApiClient;
import com.github.poad.openjdk.finder.backend.entity.JavaVersion;
import com.github.poad.openjdk.finder.backend.service.JavaVersionService;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unused")
public class AdoptOpenJdkHandler implements RequestHandler<AdoptOpenJdkHandler.Input, AdoptOpenJdkHandler.Output> {

    @Override
    public Output handleRequest(Input input, Context context) {
        var service = new JavaVersionService();
        var out = new Output();

        var client = new AdoptOpenJdkV3ApiClient();
        var versions = client.getVersions();
        out.versions = new HashSet<>(versions.values());
        out.versions.forEach(version -> service.registerJavaVersion(
                version.getId(),
                version.getVendor(),
                version.getMajorVersion(),
                version.getArch(),
                version.getVersion(),
                version.getUrl(),
                version.getType(),
                version.getImpl(),
                version.getOs(),
                version.getTimestamp()));

        return out;
    }

    public static class Input {
    }

    public static class Output {
        public Set<JavaVersion> versions;
    }
}
