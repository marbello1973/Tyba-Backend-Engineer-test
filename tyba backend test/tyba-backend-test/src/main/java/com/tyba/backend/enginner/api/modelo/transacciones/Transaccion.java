package com.tyba.backend.enginner.api.modelo.transacciones;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "transaccion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private Double monto;
    private LocalDate fecha;


    public Transaccion(DatosResgistroTransaccion datosResgistroTransaccion) {
        this.descripcion = datosResgistroTransaccion.descripcion();
        this.monto = datosResgistroTransaccion.monto();
        this.fecha = datosResgistroTransaccion.fecha();
    }
}
