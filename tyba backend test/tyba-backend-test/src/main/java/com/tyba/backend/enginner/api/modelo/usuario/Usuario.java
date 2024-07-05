package com.tyba.backend.enginner.api.modelo.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String clave;
    private Boolean estado = true;

    public Usuario(DatosActualizarUsuario datosActualizarUsuario) {
        this.nombre = datosActualizarUsuario.nombre();
        this.email = datosActualizarUsuario.email();
        this.clave = datosActualizarUsuario.clave();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return clave;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public Usuario(DatosRegistroUsuario datosRegistroUsuario) {
        this.nombre = datosRegistroUsuario.nombre();
        this.email = datosRegistroUsuario.email();
        this.clave = datosRegistroUsuario.clave();
    }

    public void desactivarUsuario() {
        this.estado = false;
    }

    public void actualizarDatos(DatosActualizarUsuario datosActualizarUsuario) {
        if(datosActualizarUsuario.nombre() != null) this.nombre = datosActualizarUsuario.nombre();
        if(datosActualizarUsuario.email() != null) this.email = datosActualizarUsuario.email();
        if(datosActualizarUsuario.clave() != null) this.clave = datosActualizarUsuario.clave();
    }


}
