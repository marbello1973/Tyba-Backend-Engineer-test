package com.tyba.backend.enginner.api.modelo.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosRegistroUsuario(

        @NotBlank(message = "{nombre}")
        String nombre,

        @NotBlank(message = "{correo.electronico}")
        @Email
        String email,

        @NotBlank(message = "{clave}")
        String clave
){}
