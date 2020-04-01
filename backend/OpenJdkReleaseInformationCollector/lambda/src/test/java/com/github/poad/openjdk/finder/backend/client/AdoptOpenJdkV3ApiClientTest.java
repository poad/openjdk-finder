package com.github.poad.openjdk.finder.backend.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AdoptOpenJdkV3ApiClientTest {
    @Test
    public void test() {
        var client = new AdoptOpenJdkV3ApiClient();
        assertNotNull(client.getVersions());
    }
}
