package com.github.poad.openjdk.finder.backend;

import com.github.poad.openjdk.finder.backend.client.AmazonCorrettoClient;
import com.github.poad.openjdk.finder.backend.client.OpenJdkApiClient;

@SuppressWarnings("unused")
public class AmazonCorrettoHandler extends LambdaHandler {
    public AmazonCorrettoHandler() {
        super("corretto");
    }

    @Override
    protected OpenJdkApiClient newOpenJdkApiClient() {
        return new AmazonCorrettoClient();
    }
}
