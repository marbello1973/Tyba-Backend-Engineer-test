## Tyaba Back-End Enginner 

## Creación Manual de Usuario en la Base de Datos e instalación API RESTful.

1. Clona el repositorio.
2. Configura tu base de datos MySQL en `application.yml`.
   ```yml
      spring:
        datasource:
          driver-class-name: com.mysql.cj.jdbc.Driver
          url: jdbc:mysql://localhost/tyba
          username: tu_usuario
          password: tu_password
      jpa:
        show-sql: true
        properties:
        hibernate:
        format_sql: true
      flyway:
        enabled: true
        url: jdbc:mysql://localhost/tyba
        user: tu_usuario
        password: tu_password
      messages:
        basename: ValidationDeMensajes
      springdoc:
       swagger-ui:
         path: /swagger-ui.html
         display-request-duration: true
   ```
4. Ejecuta la aplicación usando Spring Boot.

   Si necesitas crear manualmente un usuario en la base de datos con una contraseña encriptada, sigue estos pasos:

1. **Encripta la Contraseña con BCrypt:**
   - Utiliza [BCrypt Online Generator](https://www.bcrypt-generator.com/) u otra herramienta para encriptar la contraseña deseada. Copia el hash generado.

2. **Inserta el Usuario en la Base de Datos:**
   - Abre tu cliente de MySQL o el administrador de base de datos que prefieras.
   - Ejecuta la siguiente consulta SQL, reemplazando el email y la contraseña encriptada con los valores deseados:

   ```sql
   INSERT INTO users (email, password) VALUES ('usuario@example.com', '$2a$10$0gB3LgES0z3hS7lZol48A.V2vH7Z7UeYpPbz0B3PqufZM5/Ql9eNu');
   ```


## Uso

- Accede a [Swagger UI](http://localhost:8080/swagger-ui/index.html) para ver la documentación completa de la API.
- Login usando el endpoint `/api/v1/login` con la contraseña sin encriptar claveSegura123,.

Ejemplo de cuerpo de solicitud para crear usuario:
```json
{
  "email": "usuario@example.com",
  "password": "claveSegura123"
}
```
## Swagger 
![Swagger](C:\Users\david\Pictures\Screenshots/Captura.png)
