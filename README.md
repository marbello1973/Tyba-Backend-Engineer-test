## Tyaba Back-End Enginner 

## Creación Manual de Usuario en la Base de Datos

Si necesitas crear manualmente un usuario en la base de datos con una contraseña encriptada, sigue estos pasos:

1. **Encripta la Contraseña con BCrypt:**
   - Utiliza [BCrypt Online Generator](https://www.bcrypt-generator.com/) u otra herramienta para encriptar la contraseña deseada. Copia el hash generado.

2. **Inserta el Usuario en la Base de Datos:**
   - Abre tu cliente de MySQL o el administrador de base de datos que prefieras.
   - Ejecuta la siguiente consulta SQL, reemplazando el email y la contraseña encriptada con los valores deseados:

   ```sql
   INSERT INTO users (email, password) VALUES ('usuario@example.com', '$2a$10$0gB3LgES0z3hS7lZol48A.V2vH7Z7UeYpPbz0B3PqufZM5/Ql9eNu');
   ```





* Spring Web WEB Build web, including RESTful, applications using Spring MVC. Uses Apache Tomcat as the default embedded container.
* Spring Data JPA SQL Persist data in SQL stores with Java Persistence API using Spring Data and Hibernate.
* Lombok DEVELOPER TOOLS Java annotation library which helps to reduce boilerplate code.
* MySQL Driver SQL MySQL JDBC driver.
* Flyway Migration SQL Version control for your database so you can migrate from any version (incl. an empty database) to the latest version of the schema.
* OpenAPI Specification (formerly Swagger Specification) is an API description format for REST APIs. An OpenAPI file allows you to describe your entire API, including:
  1. Available endpoints (/users) and operations on each endpoint (GET /users, POST /users)
  2. Operation parameters Input and output for each operation
  3. Authentication methods
  4. Contact information, license, terms of use and other information.
