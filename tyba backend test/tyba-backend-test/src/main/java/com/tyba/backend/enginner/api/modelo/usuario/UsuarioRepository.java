package com.tyba.backend.enginner.api.modelo.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;



public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Page<Usuario> findAll(Pageable pageable);

    Usuario findByEmail(String email);
    //UserDetails findByEmail(String email);


/*    @Query("SELECT u.email FROM Usuario u WHERE u.email = :email")
    Optional<Usuario> findEmailByEmail(String email);*/

}
