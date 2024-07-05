package com.tyba.backend.enginner.api.services.usuarioservice;

import com.tyba.backend.enginner.api.modelo.transacciones.DatosResgistroTransaccion;
import com.tyba.backend.enginner.api.modelo.transacciones.Transaccion;
import com.tyba.backend.enginner.api.modelo.transacciones.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransaccionService {

    @Autowired
    private TransaccionRepository transaccionRepository;

    public List<Transaccion> obtenerTransaccionesHistoricas() {
        return transaccionRepository.findAll();
    }

    public Transaccion crearTransaccion(DatosResgistroTransaccion datosResgistroTransaccion) {
        Transaccion transaccion = new Transaccion(datosResgistroTransaccion);
        transaccion.setDescripcion(datosResgistroTransaccion.descripcion());
        transaccion.setMonto(datosResgistroTransaccion.monto());
        transaccion.setFecha(datosResgistroTransaccion.fecha());
        return transaccionRepository.save(transaccion);
    }

}
