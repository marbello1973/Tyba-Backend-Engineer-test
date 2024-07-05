package com.tyba.backend.enginner.api.modelo.transacciones;

import java.time.LocalDate;

public record DatosRespuestaTransacciones(
        Long id,
        String descripcion,
        Double monto,
        LocalDate fecha
) {}
