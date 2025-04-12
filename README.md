# Microservicio para el Registro de Usuarios

Este proyecto es una API RESTful construida con Spring Boot 3.3.10, base de datos en memoria H2, y JWT para la autenticación.

## Características

- Registro de usuarios
- Validación de email con expresion regular configurable
- Generación de token JWT al registrar el usuario
- Persistencia en base de datos en memoria H2
- Documentación Swagger UI

## Tecnologías

- Java 17
- Spring Boot 3.3.10
- Spring Web
- Spring Data JPA
- H2 Database
- JSON Web Token (JWT)
- Swagger (OpenAPI 3)
- JUnit 5 + Mockito

## Cómo correr el proyecto

1. Clona el repositorio
2. Ejecuta `MsUsuariosApplication.java`
3. Accede a Swagger UI:  
   [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Endpoint para acceder al servicio de Registrar Usuarios

**POST** `/api/v1/users`
```json
{
  "name": "Juan Rodriguez",
  "email": "juan@rodriguez.org",
  "password": "hunter2",
  "phones": [
    {
      "number": "1234567",
      "citycode": "1",
      "contrycode": "57"
    }
  ]
}