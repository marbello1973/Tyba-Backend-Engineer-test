package com.tyba.backend.enginner.api.services.coordenadaservice;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletionStage;

@Service
public class CoordenadaService {

    private final HttpClient httpClient;

    public CoordenadaService() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public CompletionStage<String> obtenerRestaurantesCercanos(Double latitud, Double longitud) {

        String overpassApiUrl = "https://us1.locationiq.com/v1/search?key=pk.2d54f6cedad15cdac187f1a236b62f60&format=json&q=restaurantes&";

        URI uri = UriComponentsBuilder.fromHttpUrl(overpassApiUrl)
                //.queryParam("q", "restaurantes")
                .queryParam("lat", latitud)
                .queryParam("lon", longitud)
                //.queryParam("fomat", "json")
                .queryParam("limit", 10)
                .queryParam("radius", 1000)
                .build()
                .toUri();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }
}
