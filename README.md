# Ver Pelis Plus — API con Spring Boot y MySQL

API educativa de un catálogo de películas. Usa Java 17 o superior, Spring Boot 3.5.6,
MySQL 8.0+ (Docker usa 8.4), JPA y MapStruct. El saludo funciona localmente;
la generación con IA es opcional.

## 1. Configurar las credenciales

Desde esta carpeta:

```sh
cp .env.example .env
```

Edita `.env` y cambia `DB_PASSWORD` y `MYSQL_ROOT_PASSWORD`. Si una clave contiene
símbolos, enciérrala entre comillas simples. `.env` se excluye de Git; sube solo
`.env.example`, que contiene valores de ejemplo.

## 2. Preparar MySQL

### Con Docker

Necesitas Docker Desktop o Docker Engine en ejecución:

```sh
docker compose up -d --wait
```

Esto crea `ver_pelis_plus` y el usuario indicado en `.env`. Los datos persisten en
el volumen `mysql-data`, incluso después de detener el contenedor.

### Con un MySQL ya instalado

Crea la base con tu usuario administrador:

```sh
mysql -u root -p < database/create-database.sql
```

Ajusta `DB_URL`, `DB_USERNAME` y `DB_PASSWORD` en `.env` para usar tu servidor y
un usuario con permisos sobre esa base. En desarrollo necesita crear la tabla y
leer/escribir sus registros. El puerto por defecto es 3306; si lo cambias en
Docker con `MYSQL_PORT`, ajusta también `DB_URL`.

El esquema está en **`src/main/resources/schema.sql`**. Crea `movies` con su clave
primaria, título único, duración, género, fecha, calificación decimal y estado.
`data.sql` carga diez películas de ejemplo. En el perfil `dev`, Spring ejecuta
primero el esquema, después los datos y finalmente valida las entidades JPA.
Los scripts se pueden repetir sin duplicar las películas de ejemplo.

## 3. Ejecutar la API

En macOS o Linux:

```sh
./run.sh
```

El script carga `.env` y usa el Maven Wrapper incluido. La primera ejecución
necesita internet para descargar Maven y sus dependencias. También puedes exportar
las variables tú mismo y ejecutar `./mvnw spring-boot:run`.

En Windows, configura las variables en tu terminal y usa:

```powershell
.\mvnw.cmd spring-boot:run
```

Por defecto escucha en `127.0.0.1:8090`:

```sh
curl http://localhost:8090/ver-pelis-plus/api/movies
curl http://localhost:8090/ver-pelis-plus/api/hello
```

- `GET /movies`: devuelve el catálogo como una lista de DTOs JSON.
- `GET /hello`: devuelve el saludo; no necesita ninguna clave de IA.
- Spring Data REST también publica el repositorio. Sus enlaces y operaciones se
  descubren con `GET /ver-pelis-plus/api/`.

## IA opcional

Para conservar el saludo generado con LangChain4j, configura en `.env`:

```dotenv
AI_ENABLED=true
OPENAI_API_KEY=tu_clave_propia
OPENAI_MODEL=gpt-4o-mini
```

Con `AI_ENABLED=false` no se hacen solicitudes al proveedor. Si activas IA pero
el proveedor falla, la API devuelve el saludo local. No se registran claves ni
solicitudes/respuestas de IA en modo de depuración.

## Producción

Configura `SPRING_PROFILES_ACTIVE=prod`, `DB_URL`, `DB_USERNAME` y `DB_PASSWORD`.
El puerto por defecto de este perfil es 8070; puedes cambiarlo con `SERVER_PORT`.
`SERVER_ADDRESS` permite elegir la interfaz de escucha.

En producción los scripts no se ejecutan automáticamente. Antes de arrancar,
aplica el esquema y, si quieres el catálogo de ejemplo, los datos:

```sh
mysql -h TU_HOST -u TU_USUARIO -p ver_pelis_plus < src/main/resources/schema.sql
mysql -h TU_HOST -u TU_USUARIO -p ver_pelis_plus < src/main/resources/data.sql
```

Hibernate valida la tabla existente sin borrar los datos.

## Pruebas y compilación

Las pruebas de endpoints, mapeo y saludo local no requieren MySQL ni una clave de IA:

```sh
./mvnw test
./mvnw package
```

Para comprobar además la integración real con MySQL, crea una base independiente
`ver_pelis_plus_test` y concede acceso a un usuario de pruebas. Configura las
variables `TEST_DB_URL`, `TEST_DB_USERNAME` y `TEST_DB_PASSWORD` y ejecuta:

```sh
./mvnw -Dtest=MySqlIntegrationIT test
```

Esta prueba crea/verifica el esquema, carga los datos iniciales y comprueba
persistencia y endpoints. Cada prueba revierte sus cambios mediante una transacción.
GitHub Actions también ejecuta esta integración con un MySQL temporal.

## Subir a GitHub

Sube esta carpeta como repositorio independiente. Incluye código, scripts SQL,
Maven Wrapper, `.env.example`, `compose.yaml`, pruebas y `.github/`.
`.gitignore` excluye `.env`, `target/`, logs y archivos del IDE.
