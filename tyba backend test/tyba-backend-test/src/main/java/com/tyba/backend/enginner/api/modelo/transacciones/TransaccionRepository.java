package com.tyba.backend.enginner.api.modelo.transacciones;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
}
