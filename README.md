# 🏥 Tarea Project - Bounded Contexts (DDD + Arquitectura Hexagonal)

> Un proyecto desarrollado y diseñado por **Kevin Andres Castillo Pabon**.

Este proyecto es una API REST robusta construida con **Spring Boot** utilizando **Arquitectura Hexagonal** y principios de **Domain-Driven Design (DDD)**. Está diseñado para gestionar múltiples contextos delimitados (*Bounded Contexts*) enfocados en la salud, pacientes, profesionales, y un sistema integrado de chat e inteligencia artificial.

---

## 🚀 Descripción del Proyecto

El sistema está estructurado para separar claramente la lógica de negocio de las dependencias externas (bases de datos, frameworks, etc.). Al adoptar la **Arquitectura Hexagonal**, el código se divide en tres módulos principales:

1. **`domain`**: Contiene la lógica de negocio pura, entidades, agregados y puertos de salida. No tiene dependencias de Spring.
2. **`application`**: Casos de uso y orquestación. Implementa los puertos de entrada.
3. **`infrastructure`**: Implementación de puertos de salida (bases de datos, repositorios JPA), controladores REST (Spring MVC), configuraciones de seguridad (Spring Security) y migraciones de base de datos (Flyway).

## 🛠️ Tecnologías y Herramientas

- **Java 17 / 21**
- **Spring Boot 3.x / 4.x**
  - Spring Web (REST API)
  - Spring Data JPA
  - Spring Security (Autenticación JWT)
- **PostgreSQL** (Base de datos relacional)
- **Flyway** (Control de versiones de base de datos)
- **JJWT (Java JWT)** (Implementación de tokens seguros)
- **Maven** (Gestor de dependencias)

---

## 📂 Estructura del Proyecto

El proyecto está dividido en submódulos de Maven:

```
spring_tarea_bounded-context/
├── domain/                  # Entidades del dominio, Value Objects, Puertos (Interfaces)
├── application/             # Casos de uso (Servicios de Aplicación)
└── infrastructure/          # Controladores REST, JPA Repositories, Seguridad (JWT), Flyway
    ├── src/main/resources/
    │   ├── db/migration/    # Scripts SQL de Flyway (Control de versiones BD)
    │   └── application.properties # Configuración de conexión a la BD
```

---

## 🔒 Seguridad (JWT)

El proyecto incluye un módulo de seguridad implementado en `infrastructure/security`. 
- Todas las rutas (excepto las de autenticación) están protegidas.
- Se utiliza un cifrado **BCrypt** para contraseñas.
- Se emiten tokens **JWT** sin estado (Stateless) para las sesiones.

### Endpoints Públicos de Autenticación:
- `POST /api/auth/register`: Registra un nuevo usuario en la base de datos (`app_users`).
- `POST /api/auth/authenticate`: Genera un token JWT tras validar las credenciales.

---

## ⚙️ Cómo usarlo (Instalación y Ejecución)

### 1. Requisitos Previos
- Tener instalado **Java 17** o superior.
- Tener **Maven** instalado.
- Una instancia de **PostgreSQL** corriendo (localmente o en Docker).

### 2. Configurar Base de Datos
Configura tus variables de entorno en un archivo `.env` en la raíz del proyecto o directamente en el entorno de tu sistema operativo:
```env
POSTGRES_USER=tu_usuario
POSTGRES_PASSWORD=tu_password
POSTGRES_DB=bkddb
POSTGRES_PORT=5433
```

### 3. Compilar el Proyecto
Desde la raíz del proyecto, ejecuta:
```bash
mvn clean install
```
*(Esto descargará dependencias y compilará los módulos de Dominio, Aplicación e Infraestructura).*

### 4. Ejecutar la Aplicación
Puedes iniciar el proyecto ejecutando la clase principal `TareaApplication.java` (ubicada en el módulo de infraestructura) desde tu IDE, o por consola:
```bash
cd infrastructure
mvn spring-boot:run
```

*Nota: Al arrancar, **Flyway** creará automáticamente todas las tablas (incluyendo `app_users`) de acuerdo a los scripts en `db/migration`.*

### 5. Probar con Postman
Para probar que todo funciona, abre Postman y realiza un `POST` a `http://localhost:8080/api/auth/register` con este JSON:
```json
{
  "email": "medico@ejemplo.com",
  "password": "123"
}
```
Recibirás un token JWT. Luego úsalo como **Bearer Token** para acceder al resto de los endpoints.

---

✒️ **Autor:** Kevin Andres Castillo Pabon
