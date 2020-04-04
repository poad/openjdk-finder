package com.github.poad.openjdk.finder.backend.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LibericaJdkV1ApiClientTest {
//    @Test
    public void test() {
        var client = new LibericaJdkV1ApiClient();
        var res = client.getVersions();
        assertNotNull(res);
    }
}
