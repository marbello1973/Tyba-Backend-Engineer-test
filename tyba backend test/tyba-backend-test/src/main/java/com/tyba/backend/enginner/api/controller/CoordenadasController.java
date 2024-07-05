package com.tyba.backend.enginner.api.controller;

import com.tyba.backend.enginner.api.services.coordenadaservice.CoordenadaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;


@RestController
@RequestMapping("/api/v1/coordenadas")
public class CoordenadasController {

    @Autowired
    private CoordenadaService coordenadaService;

    @GetMapping
    @Transactional
    @Operation(summary = "Restaurantes cercanos", tags = {"Restaurante Controller", "GET"})
    public CompletionStage<ResponseEntity<?>> obtenerRestaurantesCercanos(
            @RequestParam(required = false) Double latitud,
            @RequestParam(required = false) Double longitud
    )
    {
        if (latitud != null && longitud != null) {
            CompletableFuture<String> restaurantesCercanos = coordenadaService.obtenerRestaurantesCercanos(latitud, longitud).toCompletableFuture();

            return restaurantesCercanos.thenApply(ResponseEntity::ok);
        } else {
            throw new IllegalArgumentException("Se debe proporcionar coordenadas (latitud, longitud)");
        }
    }
}
