package com.github.poad.openjdk.finder.backend;

import com.github.poad.openjdk.finder.backend.client.LibericaJdkV1ApiClient;
import com.github.poad.openjdk.finder.backend.client.OpenJdkApiClient;

@SuppressWarnings("unused")
public class LibericaJdkHandler extends LambdaHandler {

    public LibericaJdkHandler() {
        super("liberica");
    }

    @Override
    protected OpenJdkApiClient newOpenJdkApiClient() {
        return new LibericaJdkV1ApiClient();
    }
}
