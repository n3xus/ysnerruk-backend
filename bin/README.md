# API
## Description
This is a Spring Boot RESTful application with:
- Maven for dependency management and build
- Spring MVC
- JPA with Hibernate

## External requirements
- A mysql server must be running locally.
    - Be sure to set up mysql with a user that has create database permissions

## Configuration
- Copy `src/main/resources/application-example.properties` to `src/main/resources/application-local.properties` and replace configuration values with those appropriate for your system
    - Important values include database name, database user, database password. Secret key is not so important for local development

## Running
- First run `mvn install -DskipTests` (for now tests are causing errors)
- Then run the server with `SPRING_PROFILES_ACTIVE=local mvn spring-boot:run`
- Now if you head to `http://localhost:8080/swagger-ui.html` you should see the swagger page
- For now default logins of `client` and `admin` have been created with passwords `client` and `admin` respectively   
