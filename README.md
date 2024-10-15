# Java Full Stack Application - Employee Management

## Overview

This project is a **Java Full Stack Application** built using **Spring Boot**. The application provides a **RESTful API** for managing employee data with standard CRUD operations (Create, Read, Update, Delete). It uses both a **relational database** (via JPA repository) for persistent storage and an **in-memory Binary Search Tree (BST)** to cache employee data and facilitate faster data access during runtime.

### Key Features:
- **REST API** for managing employees:
  - **GET**: Retrieve all employees or a specific employee by ID.
  - **POST**: Add new employees.
  - **PUT**: Update employee details.
  - **DELETE**: Remove employees by ID.
- **In-Memory Caching with Binary Search Tree (BST)**: For faster data retrieval, insertion, and deletion operations during testing or development phases.
- **Persistent Storage with JPA Repository**: For long-term storage of employee data in a relational database (like MySQL, PostgreSQL).

## Why Use a Binary Search Tree (BST)?

In this project, the **Binary Search Tree** is used as an in-memory caching solution. The BST provides faster access to employee data compared to querying a remote database (e.g., a cloud-based database). Here’s why the BST is useful:
- **Faster Read/Write**: For frequent read and write operations, the BST allows operations in `O(log n)` time complexity (assuming a balanced tree).
- **Testing Performance**: During testing or development, you can manipulate and query data in memory without accessing the database, which saves time and improves performance.
- **Reduced Latency**: By caching frequently accessed employee data in memory, the system can serve requests with lower latency compared to querying the cloud database.

### Components

1. **EmployeeController.java**: Handles HTTP requests (GET, POST, PUT, DELETE) for managing employees.
2. **EmployeeService.java**: Contains business logic. This service interacts with both the BST and the JPA repository.
3. **EmployeeRepository.java**: Provides access to the relational database for storing and retrieving employees.
4. **BinarySearchTree.java**: In-memory caching data structure used to manage employee data in RAM.
5. **Employee.java**: Model class representing an employee entity in the system.

## Endpoints

### 1. **GET /api/employees**
Retrieve a list of all employees from the in-memory BST (in-order traversal). The database is only queried when necessary.

**Example Response**:
JSON FORMAT for exchanging data between the system
[
  {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com"
  },
  {
    "id": 2,
    "firstName": "Jane",
    "lastName": "Doe",
    "email": "jane.doe@example.com"
  }
]


### 2. **GET /api/employees/{id}**
Retrieve a specific employee by ID, first searching in the BST (in-memory cache). If not found in memory, the database is queried.

### 3. **POST /api/employees**
Create a new employee. The employee is first added to the BST for immediate access and then persisted to the database.

### 4. **PUT /api/employees/{id}**
Update an existing employee by ID. Both the BST and the database are updated with the new details.

### 5. **DELETE /api/employees/{id}**
Delete an employee by ID. The employee is removed from the BST and deleted from the database.


## In-Memory Caching with BST data structure

In this application, the **Binary Search Tree (BST)** is used as an in-memory data store to cache employee records. The key benefits are:
- **Faster Operations**: Read, write, and delete operations happen faster in memory than they would in the database.
- **Development and Testing**: The BST helps simulate faster data interactions during development and testing, minimizing the need for frequent database queries.

### How It Works:
- **On Application Startup**: Employees can be loaded from the database into the BST.
- **When Creating/Updating an Employee**: Employees are inserted into the BST and saved to the database.
- **When Deleting an Employee**: The employee is removed from the BST and the database.
- **For Read Operations**: Data is first fetched from the BST, and if the data isn’t available in memory, a database query is made.

## Future Enhancements I will be implementing

1. **Implementing Self-Balancing BST**: To avoid the BST becoming unbalanced and causing slower performance, implementing an AVL or Red-Black Tree for self-balancing.
2. **Redis Integration**: Replace the BST with a production-ready caching solution like Redis for scalable caching.
3. **Database Synchronization**: Implement periodic synchronization between the BST and the database to ensure consistency.
4. **Implement Client-Side**: Implement a website for storing employees where the client can visually interact with the database manipulating employee data. 