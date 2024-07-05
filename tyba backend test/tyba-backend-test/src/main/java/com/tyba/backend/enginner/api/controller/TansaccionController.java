package com.tyba.backend.enginner.api.controller;

import com.tyba.backend.enginner.api.modelo.transacciones.DatosResgistroTransaccion;
import com.tyba.backend.enginner.api.modelo.transacciones.DatosRespuestaTransacciones;
import com.tyba.backend.enginner.api.modelo.transacciones.Transaccion;
import com.tyba.backend.enginner.api.services.usuarioservice.TransaccionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transaccion")
public class TansaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @GetMapping
    @Transactional(readOnly = true)
    @Operation(summary = "Obtener transacciones historicas", tags = {"Transaccion Controller", "GET"})
    public ResponseEntity<?> obtenerTransaccionesHistoricas() {
        return ResponseEntity.ok(transaccionService.obtenerTransaccionesHistoricas());
    }

    @PostMapping
    @Transactional
    @Operation(summary = "Registrar transaccion", tags = {"Transaccion Controller", "POST"})
    public ResponseEntity<DatosRespuestaTransacciones> registrarTransaccion(
            @RequestBody DatosResgistroTransaccion datosResgistroTransaccion
    )
    {
        Transaccion transaccion = transaccionService.crearTransaccion(datosResgistroTransaccion);
        DatosRespuestaTransacciones datos = new DatosRespuestaTransacciones(
                transaccion.getId(),
                transaccion.getDescripcion(),
                transaccion.getMonto(),
                transaccion.getFecha()
        );

        return ResponseEntity.ok(datos);
    }
}
