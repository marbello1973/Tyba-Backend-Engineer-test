package com.tyba.backend.enginner.api.services.usuarioservice;

import com.tyba.backend.enginner.api.modelo.usuario.*;
import com.tyba.backend.enginner.api.services.security.DatosJWToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.tyba.backend.enginner.api.modelo.usuario.DatosAutenticacionUsuario;
import com.tyba.backend.enginner.api.modelo.usuario.Usuario;
import com.tyba.backend.enginner.api.services.security.TokenService;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class UsuarioServices {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public DatosJWToken autenticarUsuario(DatosAutenticacionUsuario datosAutenticacionUsuario) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                datosAutenticacionUsuario.email(),
                datosAutenticacionUsuario.clave());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return new DatosJWToken(JWTtoken);
    }


    public Usuario crearUsuario(DatosRegistroUsuario datosRegistroUsuario) {
        String claveEncriptada = passwordEncoder.encode(datosRegistroUsuario.clave());
        Usuario usuario = new Usuario(datosRegistroUsuario);
        usuario.setClave(claveEncriptada);

        Usuario usuarioExistente = usuarioRepository.findByEmail(datosRegistroUsuario.email());

        if(usuarioExistente != null){
            if (!usuarioExistente.getEstado()){
                usuarioExistente.setEstado(true);
                return usuarioRepository.save(usuarioExistente);
            }else{
                throw new RuntimeException("Usuaro con email " + datosRegistroUsuario.email() + " activo");
            }
        }else{

            return usuarioRepository.save(usuario);
        }
    }

    public Map<String, Object> listarUsuario(Pageable pageable) {

        Page<Usuario> usuarios = usuarioRepository.findAll(pageable);

        //Filtrar usuario desactivado
        Page<DatosRespuestaUsuario> datos = usuarios.stream()
                .filter(user -> user.getEstado())
                .map(DatosRespuestaUsuario::new)
                .collect(Collectors.collectingAndThen(Collectors.toList(), PageImpl::new));

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("Usuarios: -> ",datos.getContent());
        respuesta.put("Total de registros: -> ", datos.getTotalElements());
        return respuesta;
    }

    public Usuario detallesUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id no existe"));
        if(!usuario.getEstado()) throw new RuntimeException("Usuario desactivado de base de datos");
        return usuario;
    }

    public Usuario actualizarUsuario(Long id, DatosActualizarUsuario datosActualizarUsuario){

        /*String claveEncriptada = passwordEncoder.encode(datosActualizarUsuario.clave());
        Usuario usuario = new Usuario(datosActualizarUsuario);
        usuario.setClave(claveEncriptada);*/

        Usuario usuario = new Usuario(datosActualizarUsuario);

        usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if(!usuario.getEstado()) throw new RuntimeException("Usuario desactivado");

        if (datosActualizarUsuario.email() != null){
            Usuario usuarioExistente = usuarioRepository.findByEmail(datosActualizarUsuario.email());
            if(usuarioExistente != null) throw new RuntimeException("Email ya registrado");
        }

        usuario.actualizarDatos(datosActualizarUsuario);

        return usuarioRepository.save(usuario);
    }

    public Usuario eliminarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        if(!usuario.getEstado()) throw new RuntimeException("Usuario desactivado");
        usuario.desactivarUsuario();
        return usuarioRepository.save(usuario);
    }
}
