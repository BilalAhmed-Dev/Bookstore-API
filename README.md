# Bookstore API - Bilal Ahmed
## Overview
The Bookstore API is a Spring Boot application designed to manage bookstore operations. It utilizes Spring Boot Starter Web for web services, Spring Boot Starter Data JPA for database interactions, and Spring Security for authentication and authorization.

## Authentication
- JWT authentication required for all endpoints except for Swagger endpoints and `/auth/*`
- Access token must be included in `Authorization` header as `Bearer <token>`

## Endpoints
Please refer to the Swagger UI for detailed endpoint documentation. Key endpoint categories include:

- **Authentication**: User registration, login, token refresh, and logout
- **Book Management**: Book CRUD operations and search functionality
- **Cart Management**: Add/remove items, modify quantities, and clear cart
- **Order Management**: Order creation and order history retrieval
- **User Management**: Username availability checks and profile operations
## Dependencies
This project uses the following key dependencies:

- **Spring Boot Starter Web**: For building web applications.
- **Spring Boot Starter Data JPA**: For database operations using JPA.
- **Spring Boot Starter Security**: For authentication and authorization.
- **Springdoc OpenAPI UI**: For generating API documentation.
- **Lombok**: For simplifying Java code with annotations.
- **MySQL Connector/J**: For connecting to MySQL databases.
- **JSON Web Tokens (JWT)**: For token-based authentication.

## Setup
To run this project, follow these steps:

1. **Clone the Repository**: Clone this repository to your local machine.
2. **Install Dependencies**: Use Maven to install all dependencies by running `mvn clean install`.
3. **Start the Application**: Run the application using `mvn spring-boot:run`.
4. **Base URL**: `http://localhost:8080`
5. **Access Swagger UI**: Navigate to `http://localhost:8080/swagger-ui/index.html` to view API documentation.