# Restaurante Web Application

Este proyecto contiene una aplicación web Java basada en Maven. A continuación se describen los requisitos y los pasos básicos para compilar y desplegar la aplicación.

## Requisitos

- **Java 17** (se utiliza para compilar la aplicación)
- **Maven 3** o superior
- **MySQL** u otra base de datos compatible
- Definir las siguientes variables de entorno para la conexión a la base de datos:
  - `DB_URL` – URL de conexión JDBC
  - `DB_USER` – usuario de la base de datos
  - `DB_PASSWORD` – contraseña del usuario

## Comandos básicos

Desde el directorio `Restaurante` se puede ejecutar Maven de la forma habitual:

```bash
mvn package   # Compila la aplicación y genera el WAR en target/
```

Otros comandos útiles son:

```bash
mvn clean      # Elimina la carpeta `target`
mvn install    # Instala el artefacto en el repositorio local
```

## Despliegue

1. Configurar las variables de entorno `DB_URL`, `DB_USER` y `DB_PASSWORD` antes de iniciar la aplicación.
2. Ejecutar `mvn package` para generar el archivo `Restaurante.war` en la carpeta `target`.
3. Copiar el WAR resultante al directorio `webapps` de su servidor Tomcat (o contenedor compatible) y reiniciar el servidor.
4. Una vez desplegado, la aplicación estará disponible según la configuración de su servidor (por ejemplo, `http://localhost:8080/Restaurante`).

