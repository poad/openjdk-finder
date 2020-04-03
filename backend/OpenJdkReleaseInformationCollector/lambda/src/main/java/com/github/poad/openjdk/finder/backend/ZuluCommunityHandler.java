package com.github.poad.openjdk.finder.backend;

import com.github.poad.openjdk.finder.backend.client.AdoptOpenJdkV3ApiClient;
import com.github.poad.openjdk.finder.backend.client.OpenJdkApiClient;

@SuppressWarnings("unused")
public class AdoptOpenJdkHandler extends LambdaHandler {
    public AdoptOpenJdkHandler() {
        super("adoptopenjdk");
    }

    @Override
    protected OpenJdkApiClient newOpenJdkApiClient() {
        return new AdoptOpenJdkV3ApiClient();
    }
}
