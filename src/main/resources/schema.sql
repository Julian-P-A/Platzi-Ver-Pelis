-- MySQL 8.0+ / 8.4. Ejecutar dentro de la base ver_pelis_plus.
-- El perfil dev lo ejecuta antes de data.sql y de validar las entidades JPA.
CREATE TABLE IF NOT EXISTS movies (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    duration INT NOT NULL,
    genre VARCHAR(255) NOT NULL,
    `date` DATE,
    classification DECIMAL(3, 2),
    status VARCHAR(1) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_movies_title UNIQUE (title)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
