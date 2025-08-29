# API de Autenticación JWT

Este proyecto es una API REST desarrollada con Spring Boot que implementa autenticación y autorización usando JWT (JSON Web Tokens).

## 🚀 Características

- **Autenticación JWT**: Sistema completo de autenticación con tokens JWT
- **Gestión de Usuarios**: CRUD completo para usuarios
- **Validación de Datos**: Validaciones robustas en DTOs
- **Documentación API**: Swagger/OpenAPI integrado
- **Manejo de Errores**: Manejador global de excepciones
- **Seguridad**: Configuración de Spring Security
- **Base de Datos**: PostgreSQL con JPA/Hibernate
- **CORS**: Configuración para aplicaciones frontend

## 🛠️ Tecnologías Utilizadas

- **Java 21**
- **Spring Boot 3.5.5**
- **Spring Security**
- **Spring Data JPA**
- **PostgreSQL**
- **JWT (JSON Web Tokens)**
- **Lombok**
- **SpringDoc OpenAPI (Swagger)**
- **Maven**

## 📋 Prerrequisitos

- Java 21 o superior
- Maven 3.6+
- PostgreSQL 12+
- IDE (IntelliJ IDEA, Eclipse, VS Code)

## 🔧 Configuración

### 1. Base de Datos

Crear una base de datos PostgreSQL:

```sql
CREATE DATABASE AuthDB;
```

### 2. Configuración de la Aplicación

El archivo `application.yml` contiene la configuración de la base de datos. Ajusta los valores según tu configuración:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/AuthDB
    username: tu_usuario
    password: tu_password
```

### 3. Variables de Entorno (Opcional)

Para mayor seguridad, puedes usar variables de entorno:

```bash
export DB_USERNAME=tu_usuario
export DB_PASSWORD=tu_password
export JWT_SECRET=tu_secreto_jwt
```

## 🚀 Ejecución

### Compilar el Proyecto

```bash
mvn clean compile
```

### Ejecutar la Aplicación

```bash
mvn spring-boot:run
```

La aplicación estará disponible en: `http://localhost:8080`

## 📚 Documentación de la API

### Swagger UI

Una vez que la aplicación esté ejecutándose, puedes acceder a la documentación interactiva de la API en:

**http://localhost:8080/swagger-ui.html**

### Endpoints Disponibles

#### Autenticación (`/api/auth`)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/auth/register` | Registrar nuevo usuario |
| POST | `/api/auth/login` | Iniciar sesión |

#### Usuarios (`/api/usuarios`)

| Método | Endpoint | Descripción | Autenticación |
|--------|----------|-------------|---------------|
| POST | `/api/usuarios` | Crear usuario | No requerida |
| GET | `/api/usuarios` | Listar usuarios | Requerida |
| GET | `/api/usuarios/{id}` | Obtener usuario por ID | Requerida |
| PUT | `/api/usuarios/{id}` | Actualizar usuario | Requerida |
| DELETE | `/api/usuarios/{id}` | Eliminar usuario | Requerida |

## 🔐 Autenticación

### Registro de Usuario

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "email": "usuario@ejemplo.com",
    "nombre": "Usuario Ejemplo",
    "password": "password123"
  }'
```

### Login

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "usuario@ejemplo.com",
    "password": "password123"
  }'
```

### Uso del Token JWT

Para endpoints protegidos, incluye el token en el header:

```bash
curl -X GET http://localhost:8080/api/usuarios \
  -H "Authorization: Bearer TU_TOKEN_JWT"
```

## 📁 Estructura del Proyecto

```
src/main/java/org/kodigo/jwt/
├── config/                 # Configuraciones
│   ├── CorsConfig.java
│   ├── JwtAuthenticationFilter.java
│   ├── OpenApiConfig.java
│   └── SecurityConfig.java
├── controller/             # Controladores REST
│   ├── AuthController.java
│   └── UsuarioController.java
├── dto/                   # Objetos de Transferencia de Datos
│   ├── AuthResponseDTO.java
│   ├── LoginDTO.java
│   ├── UsuarioDTO.java
│   └── UsuarioUpdateDTO.java
├── entity/                # Entidades JPA
│   └── Usuario.java
├── exception/             # Manejo de Excepciones
│   └── GlobalExceptionHandler.java
├── repository/            # Repositorios de Datos
│   └── UsuarioRepository.java
├── service/               # Lógica de Negocio
│   ├── AuthenticationService.java
│   ├── CustomUserDetailsService.java
│   └── JwtService.java
└── JwtKodiApplication.java
```

## 🔧 Configuración de JWT

El token JWT se configura en `application.yml`:

```yaml
jwt:
  secret: 404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970
  expiration: 86400000  # 24 horas en milisegundos
```

## 🛡️ Seguridad

- **BCrypt**: Encriptación de contraseñas
- **JWT**: Tokens de autenticación
- **Spring Security**: Configuración de seguridad
- **Validación**: Validaciones en DTOs y entidades
- **CORS**: Configuración para aplicaciones frontend

## 🧪 Testing

Para ejecutar las pruebas:

```bash
mvn test
```

## 📝 Logs

Los logs SQL se muestran en consola cuando `show-sql: true` está habilitado en la configuración.

## 🤝 Contribución

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

## 👥 Autores

- **Kodigo** - *Desarrollo inicial* - [Kodigo](https://kodigo.org)

## 🙏 Agradecimientos

- Spring Boot Team
- Spring Security Team
- JJWT Library
- PostgreSQL Community
