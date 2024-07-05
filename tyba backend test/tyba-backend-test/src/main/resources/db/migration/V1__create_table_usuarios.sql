-- Crear tabla usuario
CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    clave VARCHAR(255) NOT NULL,
    estado BOOLEAN NOT NULL
);
