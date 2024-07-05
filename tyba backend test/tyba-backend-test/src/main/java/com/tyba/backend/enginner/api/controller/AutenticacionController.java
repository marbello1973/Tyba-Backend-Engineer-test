package com.tyba.backend.enginner.api.controller;

import com.tyba.backend.enginner.api.modelo.usuario.DatosAutenticacionUsuario;
import com.tyba.backend.enginner.api.services.security.DatosJWToken;
import com.tyba.backend.enginner.api.services.usuarioservice.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/login")
public class AutenticacionController {

    @Autowired
    private UsuarioServices usuarioServices;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosJWToken> autenticarUsuario(
            @RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario)
    {
        DatosJWToken JWTtoken = usuarioServices.autenticarUsuario(datosAutenticacionUsuario);
        return ResponseEntity.ok(JWTtoken);
    }

}
