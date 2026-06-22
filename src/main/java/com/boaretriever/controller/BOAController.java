package com.boaretriever.controller;

import com.boaretriever.model.BOA;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BOAController {
    private String URL;

    public BOAController(String URL) {
        this.URL = URL;
    }

    public BOA[] GetBOAData() {
        BOA[] Data = null;
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest peticion = HttpRequest.newBuilder()
                    .uri(URI.create(this.URL))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(
                    peticion,
                    HttpResponse.BodyHandlers.ofString()
            );

            int statusCode = response.statusCode();
            if (statusCode != 200) {
                System.out.println("Error: Received status code " + statusCode);
                return null;
            }

            String responseBody = response.body();
            if (responseBody == null) {
                throw new IOException("Response body is null");
            }

            Gson gson = new Gson();
            Data = gson.fromJson(responseBody, BOA[].class);
            return Data;

        } catch (IOException e) {
            System.out.println("IOException: Error getting BOA data: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception: Error getting BOA data: " + e.getMessage());
        }

        return Data;
    }
}
