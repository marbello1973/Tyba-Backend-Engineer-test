package com.tyba.backend.enginner.api.controller;

import com.tyba.backend.enginner.api.modelo.usuario.DatosActualizarUsuario;
import com.tyba.backend.enginner.api.modelo.usuario.DatosRegistroUsuario;
import com.tyba.backend.enginner.api.modelo.usuario.DatosRespuestaUsuario;
import com.tyba.backend.enginner.api.modelo.usuario.Usuario;
import com.tyba.backend.enginner.api.services.usuarioservice.UsuarioServices;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.net.URI;



@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioServices usuarioServices;

    @PostMapping
    @Transactional
    @Operation(summary = "Registro de usuario", tags = {"Usuario Controller", "POST"})
    public ResponseEntity<DatosRespuestaUsuario> crearUsuario(
            @RequestBody DatosRegistroUsuario datosRegistroUsuario, UriComponentsBuilder uriComponentsBuilder) {
        Usuario usuario = usuarioServices.crearUsuario(datosRegistroUsuario);
        DatosRespuestaUsuario datos = new DatosRespuestaUsuario(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail()
        );
        URI url = uriComponentsBuilder.path("/api/v1/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(datos);
    }

    @GetMapping()
    @Operation(summary = "Listado de usuarios", tags = {"Usuario Controller", "GET"})
    public ResponseEntity<Map<String, Object>> listarUsuario(@PageableDefault(size = 10) Pageable pageable) {
        Map<String, Object> respuesta = usuarioServices.listarUsuario(pageable);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Detalles de un usuario", tags = {"Usuario Controller", "GET"})
    public ResponseEntity<DatosRespuestaUsuario> detallesUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioServices.detallesUsuario(id);
        DatosRespuestaUsuario datos = new DatosRespuestaUsuario(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail()
        );
        return ResponseEntity.ok(datos);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar usuario", tags = {"Usuario Controller", "PUT"})
    public ResponseEntity<DatosRespuestaUsuario> actualizarUsuario(@PathVariable Long id, @RequestBody DatosActualizarUsuario datosActualizarUsuario) {
        Usuario usuario = usuarioServices.actualizarUsuario(id, datosActualizarUsuario);
        DatosRespuestaUsuario datos = new DatosRespuestaUsuario(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail()
        );
        return ResponseEntity.ok(datos);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar usuario", tags = {"Usuario Controller", "DELETE"})
    public ResponseEntity<DatosRespuestaUsuario> eliminarUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioServices.eliminarUsuario(id);
        DatosRespuestaUsuario datos = new DatosRespuestaUsuario(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail()
        );
        return ResponseEntity.ok(datos);
    }

}
