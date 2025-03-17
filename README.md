# Project Overview
- This is a Spring Boot RESTful API that calculates reward points for customers based on their transactions.
- A customer earns 2 points for every dollar spent above $100.
- A customer earns 1 point for every dollar spent between $50 and $100.
- Transactions are recorded for three months, and total reward points per customer per month are calculated.

# Tech Stack
- Java (Spring Boot)
- Spring Web (REST API)
- Spring Data JPA (H2 Database)
- H2 Database (In-memory database)
- JUnit & Mockito (Testing)
- Maven (Dependency Management)
- GitHub (Version Control)

# Project Structure
- src/
- ├── main/
- │   ├── java/com.javaAssignment.javaAssignment/
- │   │   ├── controller/     # REST Controllers
            - ├──CustomerController.java
- │   │   ├── service/        # Business Logic
            - ├──CustomerService.java
            - ├──RewardService.java
- │   │   ├── repository/     # Database Repositories 
            - ├──TransactionRepository.java
            - ├──CustomerRepository.java
- │   │   ├── entity/          # Entity Classes
            - ├──Customer.java
            - ├──Transaction.java
- │   │   ├── exception/            # Exception Handling
            - ├──GlobalExceptionHandler.java
            - ├──ErrorDetails.java
            - ├──CustomerNotFoundException.java
            - ├──InvalidAmountException.java
- │   │   ├── JavaAssignmentApplication.java  # Main Spring Boot Class
- │   ├── resources/
- │   │   ├── application.properties  # Configuration
- │   ├── test/java/com.javaAssignment.javaAssignment/  # JUnit Test Classes
          - ├──CustomerServiceForTotalRewardTest.java
          - ├──MonthlyRewardTest.java
          - ├──AddTransactionTest.java
          - ├──testAddCustomer.java

# Database Configuration (H2)
- By default, the project uses an in-memory H2 database.
- To access the H2 Console, go to:
- http://localhost:8080/h2-console

- JDBC URL: jdbc:h2:mem:testdb
- Username: sa
- Password: password

# POST Endpoint to Add Customer
- http://localhost:8080/customers/addCustomer/{customerName}


# POST Endpoint to Add Transaction
- http://localhost:8080/customers/{customerId}/transactions


# GET Endpoint to calculate monthly reward
- http://localhost:8080/customers/{customerId}/rewards/{month}


# GET Endpoint to calculate total reward
- http://localhost:8080/customers/{customerId}/rewards/total
           
