# Book Library Management System

A simple **Library Management System** built with **Spring Boot** to manage books, authors, employees, members, and borrowing records.  
It allows adding books to library, borrowing and returning books, tracks book quantities, and ensures cannot exceed borrowing limits.

## Features

- Manage **Authors** and **Books** (add, list, track quantities)
- Manage **Members** and **Employees**
- Borrow and return books
- Prevent borrowing if:
    - Book quantity is 0
    - Member has reached max active borrowings
    - Member is inactive
    - Member already borrowed the same book
- Automatic calculation of **return date** based on borrowing period
- REST API endpoints for all operations

## Technologies / Stack Used

- **Java 25**
- **Spring Boot 4.0.3** (Web, Data JPA)
- **Spring Data JPA** for database operations
- **MapStruct** for DTO ↔ Entity mapping
- **H2 Database** (in-memory, for testing) or **MySQL** (optional for production)
- **JUnit 6** & **Mockito** for unit testing
- **Maven** for build and dependency management  