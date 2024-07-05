-- V1__Create_Transaccion_Table.sql

CREATE TABLE transaccion (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(255) NOT NULL,
    monto DOUBLE NOT NULL,
    fecha DATE NOT NULL
);