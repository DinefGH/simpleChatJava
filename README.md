# SimpleChatJava

SimpleChatJava is a sample project built for learning the basics of Spring Boot. 
It demonstrates how to create REST APIs, implement JWT-based authentication, and test endpoints using Postman. 
This project includes user registration, user login with JWT token generation, 
and a messaging API where messages are associated with users.


## Features

- ### User Registration:

    Register new users with username, email, and password.


- ### JWT-Based Authentication:
    Authenticate users via a login endpoint that returns a JWT token. The token is then used to secure other endpoints.


- ### Messaging API:

    - Send Message: A POST endpoint to send a message associated with a user.
    - Retrieve Messages: A GET endpoint to retrieve all messages.
  

  - ### Test-Driven Development (TDD):
    The project includes examples of unit and integration tests using Spring Boot's testing support and MockMvc.


- ### Spring Security Integration:

    Secures endpoints using Spring Security and a custom JWT filter.


## Technologies
- Java 17 (or later)
- Spring Boot 3.4.3
- Spring Data JPA
- Spring Security
- JJWT (for JWT token management)
- PostgreSQL or H2 (for persistence)
- Maven
- Postman (for API testing)
- (Optional) Vue.js for front-end integration


## Getting Started
### Prerequisites
- Java 17 or later
- Maven
- PostgreSQL (if using PostgreSQL instead of the default H2 database)
- IDE: IntelliJ IDEA or any other IDE of your choice


## Installation
### 1. Clone the Repository:

```sh
git clone https://github.com/DinefGH/SimpleChatJava.git
cd SimpleChatJava
```

### 2. Configure the Database:


In src/main/resources/application.properties, configure your database connection. For example, to use PostgreSQL:



```
spring.datasource.url=jdbc:postgresql://localhost:5432/simpleChatDb
spring.datasource.username=postgres
spring.datasource.password=yourpassword
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

If you prefer H2 for testing, ensure these lines are set:

```
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

### 3. Configure JWT:

In JwtUtil.java (located under src/main/java/com/your_path/simplechatjava/security), update the SECRET_KEY to a strong secret key.


### 4. Build and Run:

Use Maven to build and run the application:

```sh
mvn spring-boot:run
```

The application will start on http://localhost:8080.


Project Structure

```
SimpleChatJava
│   pom.xml
│
└───src
    ├───main
    │   ├───java
    │   │   └───com/dinef/simplechatjava
    │   │       ├───controller
    │   │       │       AuthController.java
    │   │       │       MessageController.java
    │   │       │       UserController.java
    │   │       ├───dto
    │   │       │       LoginRequest.java
    │   │       │       LoginResponse.java
    │   │       ├───model
    │   │       │       Message.java
    │   │       │       User.java
    │   │       ├───repository
    │   │       │       UserRepository.java
    │   │       ├───security
    │   │       │       CustomUserDetails.java
    │   │       │       CustomUserDetailsService.java
    │   │       │       JwtRequestFilter.java
    │   │       │       JwtUtil.java
    │   │       └───service
    │   │               MessageService.java
    │   │               UserService.java
    │   └───resources
    │           application.properties
    └───test
        └───java
                [Your Test Classes]

```


## Additional Notes:

#  Security Considerations:

- This project is for learning purposes. In a production environment, consider additional security measures like stronger password policies, error handling, logging, and CORS configurations.

##  Extending the Project:
- You can add more features such as pagination, advanced role-based access control, or a front-end using Vue.js.