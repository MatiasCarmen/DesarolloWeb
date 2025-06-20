-- Stored procedures for user validation and unique data checks

-- Validate username and password
DROP PROCEDURE IF EXISTS sp_validar_usuario;
DELIMITER //
CREATE PROCEDURE sp_validar_usuario(
    IN p_username VARCHAR(50),
    IN p_password VARCHAR(255)
)
BEGIN
    SELECT id, username, rol
    FROM usuarios
    WHERE username = p_username AND password = p_password;
END //
DELIMITER ;

-- Check unique DNI, phone and email on insert/update
DROP PROCEDURE IF EXISTS sp_validar_datos_unicos;
DELIMITER //
CREATE PROCEDURE sp_validar_datos_unicos(
    IN p_dni VARCHAR(20),
    IN p_telefono VARCHAR(20),
    IN p_correo VARCHAR(100),
    OUT p_codigo INT
)
BEGIN
    IF EXISTS(SELECT 1 FROM usuarios WHERE dni = p_dni) THEN
        SET p_codigo = 1; -- DNI duplicado
    ELSEIF EXISTS(SELECT 1 FROM usuarios WHERE telefono = p_telefono) THEN
        SET p_codigo = 2; -- Teléfono duplicado
    ELSEIF EXISTS(SELECT 1 FROM usuarios WHERE correo = p_correo) THEN
        SET p_codigo = 3; -- Correo duplicado
    ELSE
        SET p_codigo = 0; -- Sin duplicados
    END IF;
END //
DELIMITER ;
