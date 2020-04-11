package com.github.poad.openjdk.finder.backend;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.github.poad.openjdk.finder.backend.client.OpenJdkApiClient;
import com.github.poad.openjdk.finder.backend.entity.JavaVersion;
import com.github.poad.openjdk.finder.backend.service.JavaVersionService;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class LambdaHandler implements RequestHandler<LambdaHandler.Input, LambdaHandler.Output> {

    private final String distribution;

    public LambdaHandler(String distribution) {
        this.distribution = distribution;
    }

    @Override
    public LambdaHandler.Output handleRequest(LambdaHandler.Input input, Context context) {
        var service = new JavaVersionService(distribution);
        var out = new LambdaHandler.Output();

        var client = newOpenJdkApiClient();
        var versions = client.getVersions();
        out.versions = new HashSet<>(versions.values());
        out.versions.forEach(service::registerJavaVersion);
        service.delete(out.versions.stream().map(JavaVersion::getId).collect(Collectors.toList()));

        return out;
    }

    protected abstract OpenJdkApiClient newOpenJdkApiClient();

    public static class Input {
    }

    public static class Output {
        public Set<JavaVersion> versions;
    }

}
