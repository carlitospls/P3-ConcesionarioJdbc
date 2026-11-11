DROP TABLE IF EXISTS coche;
DROP TABLE IF EXISTS marca;

CREATE TABLE marca (
    codigo INT          AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE coche (
    codigo           INT          AUTO_INCREMENT PRIMARY KEY,
    nombre           VARCHAR(100) NOT NULL,
    precio           DOUBLE       NOT NULL,
    codigo_marca INT          NOT NULL,
    CONSTRAINT fk_coche_marca
        FOREIGN KEY (codigo_marca) REFERENCES marca (codigo)
);
