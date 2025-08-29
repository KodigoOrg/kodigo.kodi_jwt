# Ejemplos de Uso de la API

Este archivo contiene ejemplos prácticos de cómo usar los endpoints de la API de autenticación JWT.

## 🔐 Autenticación

### 1. Registrar un Nuevo Usuario

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "email": "usuario@ejemplo.com",
    "nombre": "Usuario Ejemplo",
    "password": "password123"
  }'
```

**Respuesta esperada:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "email": "usuario@ejemplo.com",
  "nombre": "Usuario Ejemplo"
}
```

### 2. Iniciar Sesión

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "usuario@ejemplo.com",
    "password": "password123"
  }'
```

**Respuesta esperada:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "email": "usuario@ejemplo.com",
  "nombre": "Usuario Ejemplo"
}
```

## 👥 Gestión de Usuarios

### 3. Crear Usuario (Sin Autenticación)

```bash
curl -X POST http://localhost:8080/api/usuarios \
  -H "Content-Type: application/json" \
  -d '{
    "email": "nuevo@ejemplo.com",
    "nombre": "Nuevo Usuario",
    "password": "password123"
  }'
```

### 4. Listar Usuarios (Requiere Autenticación)

```bash
curl -X GET http://localhost:8080/api/usuarios \
  -H "Authorization: Bearer TU_TOKEN_JWT"
```

**Respuesta esperada:**
```json
[
  {
    "id": "123e4567-e89b-12d3-a456-426614174000",
    "email": "usuario@ejemplo.com",
    "nombre": "Usuario Ejemplo",
    "role": "USER",
    "creadoEn": "2024-01-01T10:00:00"
  }
]
```

### 5. Obtener Usuario por ID (Requiere Autenticación)

```bash
curl -X GET http://localhost:8080/api/usuarios/123e4567-e89b-12d3-a456-426614174000 \
  -H "Authorization: Bearer TU_TOKEN_JWT"
```

### 6. Actualizar Usuario (Requiere Autenticación)

```bash
curl -X PUT http://localhost:8080/api/usuarios/123e4567-e89b-12d3-a456-426614174000 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer TU_TOKEN_JWT" \
  -d '{
    "email": "usuario.actualizado@ejemplo.com",
    "nombre": "Usuario Actualizado"
  }'
```

### 7. Eliminar Usuario (Requiere Autenticación)

```bash
curl -X DELETE http://localhost:8080/api/usuarios/123e4567-e89b-12d3-a456-426614174000 \
  -H "Authorization: Bearer TU_TOKEN_JWT"
```

## 🧪 Casos de Error

### 8. Email Duplicado en Registro

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "email": "usuario@ejemplo.com",
    "nombre": "Usuario Duplicado",
    "password": "password123"
  }'
```

**Respuesta esperada:**
```json
{
  "timestamp": "2024-01-01T10:00:00",
  "status": 409,
  "error": "Conflict",
  "message": "El email ya está registrado"
}
```

### 9. Credenciales Inválidas

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "usuario@ejemplo.com",
    "password": "password_incorrecto"
  }'
```

**Respuesta esperada:**
```json
{
  "timestamp": "2024-01-01T10:00:00",
  "status": 401,
  "error": "Unauthorized",
  "message": "Credenciales inválidas"
}
```

### 10. Token Inválido

```bash
curl -X GET http://localhost:8080/api/usuarios \
  -H "Authorization: Bearer TOKEN_INVALIDO"
```

**Respuesta esperada:**
```json
{
  "timestamp": "2024-01-01T10:00:00",
  "status": 401,
  "error": "Unauthorized",
  "message": "Token inválido"
}
```

## 📝 Ejemplos con JavaScript (Fetch API)

### Registro de Usuario

```javascript
const registerUser = async () => {
  try {
    const response = await fetch('http://localhost:8080/api/auth/register', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        email: 'usuario@ejemplo.com',
        nombre: 'Usuario Ejemplo',
        password: 'password123'
      })
    });
    
    const data = await response.json();
    console.log('Token:', data.token);
    localStorage.setItem('jwt_token', data.token);
  } catch (error) {
    console.error('Error:', error);
  }
};
```

### Login

```javascript
const loginUser = async () => {
  try {
    const response = await fetch('http://localhost:8080/api/auth/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        email: 'usuario@ejemplo.com',
        password: 'password123'
      })
    });
    
    const data = await response.json();
    console.log('Token:', data.token);
    localStorage.setItem('jwt_token', data.token);
  } catch (error) {
    console.error('Error:', error);
  }
};
```

### Obtener Usuarios (Con Token)

```javascript
const getUsers = async () => {
  try {
    const token = localStorage.getItem('jwt_token');
    const response = await fetch('http://localhost:8080/api/usuarios', {
      method: 'GET',
      headers: {
        'Authorization': `Bearer ${token}`
      }
    });
    
    const users = await response.json();
    console.log('Usuarios:', users);
  } catch (error) {
    console.error('Error:', error);
  }
};
```

## 🔧 Script de Prueba Completo

```bash
#!/bin/bash

# Variables
API_URL="http://localhost:8080"
EMAIL="test@ejemplo.com"
PASSWORD="password123"
NOMBRE="Usuario Test"

echo "🧪 Iniciando pruebas de la API..."

# 1. Registrar usuario
echo "📝 Registrando usuario..."
REGISTER_RESPONSE=$(curl -s -X POST "$API_URL/api/auth/register" \
  -H "Content-Type: application/json" \
  -d "{
    \"email\": \"$EMAIL\",
    \"nombre\": \"$NOMBRE\",
    \"password\": \"$PASSWORD\"
  }")

echo "Respuesta de registro: $REGISTER_RESPONSE"

# Extraer token
TOKEN=$(echo $REGISTER_RESPONSE | grep -o '"token":"[^"]*"' | cut -d'"' -f4)

if [ -z "$TOKEN" ]; then
    echo "❌ Error: No se pudo obtener el token"
    exit 1
fi

echo "✅ Token obtenido: ${TOKEN:0:50}..."

# 2. Obtener usuarios
echo "👥 Obteniendo lista de usuarios..."
USERS_RESPONSE=$(curl -s -X GET "$API_URL/api/usuarios" \
  -H "Authorization: Bearer $TOKEN")

echo "Respuesta de usuarios: $USERS_RESPONSE"

# 3. Login
echo "🔐 Probando login..."
LOGIN_RESPONSE=$(curl -s -X POST "$API_URL/api/auth/login" \
  -H "Content-Type: application/json" \
  -d "{
    \"email\": \"$EMAIL\",
    \"password\": \"$PASSWORD\"
  }")

echo "Respuesta de login: $LOGIN_RESPONSE"

echo "✅ Pruebas completadas!"
```

## 📚 Documentación Swagger

Para ver la documentación interactiva completa de la API, visita:

**http://localhost:8080/swagger-ui.html**

Allí podrás:
- Ver todos los endpoints disponibles
- Probar los endpoints directamente desde el navegador
- Ver los esquemas de datos
- Ejecutar requests con autenticación
