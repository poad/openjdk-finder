package com.github.poad.openjdk.finder.backend;

import com.github.poad.openjdk.finder.backend.client.OpenJdkApiClient;
import com.github.poad.openjdk.finder.backend.client.SapMachineGitHubApiClient;

@SuppressWarnings("unused")
public class SapMachineHandler extends LambdaHandler {
    public SapMachineHandler() {
        super("sapmachine");
    }

    @Override
    protected OpenJdkApiClient newOpenJdkApiClient() {
        return new SapMachineGitHubApiClient();
    }
}
