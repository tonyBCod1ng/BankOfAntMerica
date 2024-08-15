# BankOfAntMerica

## Project Overview
BankOfAntMerica is a Spring Boot application that simulates banking operations. It allows users to manage various types of accounts, perform transactions, and monitor their banking activities, with security handled by Spring Security.

## Features
- Account Management:
  - Create new accounts (Debit, Credit, and Savings)
  - View account details
- Transactions:
  - Perform account transfers
  - Monitor transaction history
- User Authentication and Authorization (using Spring Security)

## Technologies Used
- Java (Oracle OpenJDK 21.0.2 - aarch64)
- Spring Boot
- Spring Security
- MySQL
- Hibernate
- JSP (JavaServer Pages)
- Tomcat (embedded)

## Setup and Installation

### Prerequisites
- Java JDK 21
- MySQL Server
- Maven (for dependency management)

### Database Setup
1. Create a MySQL database named `bank_of_antmerica`
2. Update the `application.properties` file with your MySQL credentials if different from the defaults

### Running the Application
1. Clone the repository
2. Navigate to the project directory
3. Run `mvn spring-boot:run`
4. The application will be available at `http://localhost:8080`

## Configuration

The application uses the following key configurations:

- Database: MySQL
- Connection Pool: HikariCP
- ORM: Hibernate
- View Technology: JSP
- Character Encoding: UTF-8
- Security: Spring Security

For detailed configuration, refer to the `application.properties` file in the project.

## Security
This application uses Spring Security for authentication and authorization. Ensure that you configure your security settings appropriately for your environment.

## Usage
(To be filled with specific usage instructions, such as how to create an account, perform transfers, login/logout procedures, etc.)

## API Endpoints
(If applicable, list and describe any REST API endpoints)

## Testing
(Describe the testing procedures, including how to run tests)

## Deployment
(Provide instructions for deploying the application to a production environment)

## Contributing
(Guidelines for contributing to the project, if applicable)

## License
(License information to be added)

## Contact
Developed by: tonybcod1ng

---

This project is currently in development (Version 0.0.1-SNAPSHOT).

## Logging
- Application logging level: DEBUG
- Spring Framework logging level: INFO
