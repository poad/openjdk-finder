package com.github.poad.openjdk.finder.backend.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public abstract class JsonHttpClient {
    private final ObjectMapper mapper = new ObjectMapper();
    protected <T> T request(HttpClient client, String url, Class<T> type) {
        try {
            var response = request(client, url);
            return mapper.readValue(response.body(), type);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    protected <T> T request(HttpClient client, String url, TypeReference<T> type) {
        try {
            var response = request(client, url);
            return mapper.readValue(response.body(), type);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private HttpResponse<String> request(HttpClient client, String url) {
        var request = HttpRequest
                .newBuilder(URI.create(url))
                .GET()
                .build();
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
