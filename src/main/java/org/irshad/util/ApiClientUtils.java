package org.irshad.util;

import com.github.fge.jsonschema.core.report.LogLevel;
import io.swagger.v3.oas.models.PathItem;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApiClientUtils {

    private static final Logger logger = Logger.getLogger(ApiClientUtils.class.getName());

    HttpClient client = HttpClient.newHttpClient();

    public void runTest(String url, PathItem.HttpMethod httpMethod) {
        HttpRequest request = null;
        switch (httpMethod) {
            case GET -> {
                request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

                break;
            }
            case POST -> {

                break;
            }
            case PUT -> {
                break;
            }
            case HEAD -> {
                break;
            }
            case PATCH -> {
                break;
            }
            case TRACE -> {
                break;
            }
            case DELETE -> {
                break;
            }
            case OPTIONS -> {
                break;
            }
        }
        if (request != null) {
            runClient(request);
        }
    }

    private void runClient(HttpRequest request) {
        CompletableFuture<HttpResponse<String>> responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        // Process the response when it's complete
        responseFuture.thenAccept(response -> {
            if (response.statusCode() == 200) {
                // Proceed if response is OK
                logger.info("Successful response: " + response.body());
            } else {
                // Handle other status codes
                logger.log(Level.WARNING, "Failed with status code: " + response.statusCode());
            }
        }).exceptionally(ex -> {
            logger.log(Level.SEVERE, "Request  failed: " + ex.getMessage());
            return null; // Handle exception
        });

        responseFuture.join();
    }
}
