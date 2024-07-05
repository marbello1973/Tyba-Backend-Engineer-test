package com.tyba.backend.enginner.api.modelo.transacciones;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record DatosResgistroTransaccion(

        @NotBlank
        String descripcion,
        @NotBlank
        Double monto,
        @NotBlank
        LocalDate fecha
) {}
