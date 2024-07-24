# Match Service

This is a Spring Boot application that provides a RESTful API for managing cricket matches.

## Prerequisites

- Java 11 or 17
- Maven

## Running the Application

1. Clone the repository:
    ```sh
    git clone <repository-url>
    cd match-service
    ```

2. Build and run the application:
    ```sh
    mvn spring-boot:run
    ```

3. Access the H2 console:
    - URL: `http://localhost:8080/h2-console`
    - JDBC URL: `jdbc:h2:mem:testdb`
    - Username: `sa`
    - Password: `password`

4. Access the Swagger UI:
    - URL: `http://localhost:8080/swagger-ui.html`

## API Endpoints

- `POST /matches`: Create a new match
- `GET /matches/{id}`: Retrieve a match by ID
- `PUT /matches/{id}`: Update a match by ID
- `DELETE /matches/{id}`: Delete a match by ID

## Running Tests

To run tests, use the following command:
```sh
mvn test
