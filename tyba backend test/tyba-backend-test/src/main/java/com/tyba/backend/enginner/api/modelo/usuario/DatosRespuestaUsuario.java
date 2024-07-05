package com.tyba.backend.enginner.api.modelo.usuario;

public record DatosRespuestaUsuario(
        Long id,
        String nombre,
        String email

){
    public DatosRespuestaUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail()
        );
    }
}
