CREATE DATABASE IF NOT EXISTS db_ecommerce_tantaricoricardo;
USE db_ecommerce_tantaricoricardo;

CREATE TABLE IF NOT EXISTS roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS usuarios_roles (
    usuario_id BIGINT NOT NULL,
    rol_id BIGINT NOT NULL,
    PRIMARY KEY (usuario_id, rol_id),
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    CONSTRAINT fk_rol FOREIGN KEY (rol_id) REFERENCES roles(id)
);

CREATE TABLE IF NOT EXISTS productos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    precio_compra DECIMAL(10,2) NOT NULL,
    precio_venta DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL,
    marca VARCHAR(50) NOT NULL,
    codigo_barras VARCHAR(50) NOT NULL UNIQUE,
    fecha_ingreso DATE NOT NULL
);

INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN'), ('ROLE_USER');

-- Contraseña encriptada con BCrypt para ambos usuarios: "123456"
INSERT INTO usuarios (username, password) VALUES 
('ricardo', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy'),
('admin', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy');

INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (1, 1), (2, 1);

INSERT INTO productos (nombre, categoria, precio_compra, precio_venta, stock, marca, codigo_barras, fecha_ingreso) VALUES
('Laptop Gamer Nitro 5', 'Computo', 2800.00, 3499.00, 10, 'Acer', '775000100001', '2026-11-11'),
('Mouse Inalambrico G305', 'Perifericos', 110.00, 169.90, 25, 'Logitech', '775000100002', '2026-11-11');
