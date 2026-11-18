CREATE DATABASE IF NOT EXISTS airtortilla_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE airtortilla_db;

CREATE TABLE IF NOT EXISTS usuarios (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    rol ENUM('ADMIN', 'EMPLEADO') NOT NULL DEFAULT 'EMPLEADO',
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS reservas (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        destino VARCHAR(10) NOT NULL,
    origen VARCHAR(10) NOT NULL,
    nombre_pasajero VARCHAR(100) NOT NULL,
    fecha_vuelo VARCHAR(20) NOT NULL,
    localizador VARCHAR(20) DEFAULT '-'
    );

CREATE TABLE IF NOT EXISTS auditoria_logs (
                                              id INT AUTO_INCREMENT PRIMARY KEY,
                                              usuario_id INT NULL,
                                              username VARCHAR(50) NOT NULL,
    accion VARCHAR(50) NOT NULL,
    detalle TEXT NOT NULL,
    fecha_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE SET NULL
    );

-- Usuarios iniciales: admin / admin123 | empleado / emp123
INSERT IGNORE INTO usuarios (username, password_hash, rol) VALUES
('admin', SHA2('admin123', 256), 'ADMIN'),
('empleado', SHA2('emp123', 256), 'EMPLEADO');