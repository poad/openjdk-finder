package com.github.poad.openjdk.finder.backend;

import com.github.poad.openjdk.finder.backend.client.OpenJdkApiClient;
import com.github.poad.openjdk.finder.backend.client.ZuluCommunityV1ApiClient;

@SuppressWarnings("unused")
public class ZuluCommunityHandler extends LambdaHandler {
    public ZuluCommunityHandler() {
        super("zulu");
    }

    @Override
    protected OpenJdkApiClient newOpenJdkApiClient() {
        return new ZuluCommunityV1ApiClient();
    }
}
