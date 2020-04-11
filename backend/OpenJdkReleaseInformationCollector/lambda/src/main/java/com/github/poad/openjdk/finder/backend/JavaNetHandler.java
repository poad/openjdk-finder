package com.github.poad.openjdk.finder.backend;

import com.github.poad.openjdk.finder.backend.client.JavaNetClient;
import com.github.poad.openjdk.finder.backend.client.OpenJdkApiClient;

@SuppressWarnings("unused")
public class JavaNetHandler extends LambdaHandler {
    public JavaNetHandler() {
        super("openjdk");
    }

    @Override
    protected OpenJdkApiClient newOpenJdkApiClient() {
        return new JavaNetClient();
    }
}
