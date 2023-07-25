# Trade Union: Full-Stack Spring Boot and React Application

## Overview
A comprehensive full-stack web application built using Spring Boot for the backend and React for the frontend. In this project, I tried to implement a table view and the ability to process information using tables.


## Table of Contents
- [Project Overview](#overview)
- [Features](#features)
- [Technologies Used](#technologies%20used)
- [Project Roadmap and Issues](#Project%20Roadmap%20and%20issues)
- [Getting Started](##getting%20started)
    - [Installation](#installation)
    - [Configuration](#configuration)
    - [Start Application](#running%20the%20application)
- [API Documentation](#api-documentation)
- [User Interface Overview](#user%20interface%20overview)

## Features
- User authentication and authorization. With different roles and permissions (the same Roles can have different permissions)
- CRUD operations for managing data
- Table view of data
- Custom validation logic

## Technologies Used
- List the technologies and tools used in your project. For example:
    - Backend:
        - Spring Boot
        - Spring Security (JWT)
        - Hibernate
        - PostgreSQL
        - Flyway migration
    - Frontend:
        - React
        - DevExpress

## Project Roadmap and Issues

### Current Issues
📌 Еhe frontend of the application lacks proper exception handling, which leads to poor user experience when unexpected errors occur.

### Refactoring and Optimization

📌 Rewrite State Manager to Redux - Currently, the state management in the frontend is done using `useContext`, which can lead to performance issues and complex code. We should refactor the state management to use Redux for better organization and scalability.

📌 Frontend Code Refactoring - Refactor frontend code to improve maintainability and reduce spaghetti  code O-o.

## Getting Started
To get started with the Full-Stack Spring Boot and React Application, follow the steps below:
### Installation
Before running the application, make sure you have the following installed on your system:
1. **Java Development Kit (JDK)**: Ensure you have Java 11 or later installed
2. **Node.js**: Install Node.js, which includes npm (Node Package Manager)
3. **PostgreSQL Database**: Install PostgreSQL on your system, and set up a database for the application
4. Clone the repository to your local machine using Git:
```bash
git clone https://github.com/your-username/fullstack-spring-react.git
cd fullstack-spring-react
```

### Configuration
2. Create an `application.properties` file in the `src/main/resources` directory based on the provided `application.properties.template`. Replace the placeholders `${DB_USERNAME}`, `${DB_PASSWORD}`, and `${JWT_SECRET}` with your PostgreSQL database username, password, and JWT secret respectively.
3. Set up environment variables:
- On Unix-based systems (Linux or macOS):
```bash
export DB_USERNAME=your_database_username
export DB_PASSWORD=your_database_password
export JWT_SECRET=your_jwt_secret
```
- On Windows:
```bash
set DB_USERNAME=your_database_username
set DB_PASSWORD=your_database_password
set JWT_SECRET=your_jwt_secret
```

4. Install frontend dependencies and build the React application:
```bash
cd frontend
npm install
npm run build
cd ..
```

### Running the Application

Now you're all set to run the full-stack application:

1. Start the Spring Boot backend server (if you haven't already):

`./mvnw spring-boot:run`

The backend server will be running at `http://localhost:8090.

2. Start the React frontend server:
   `cd frontend npm start`

The frontend server will be running at `http://localhost:3000`.

## API Documentation
### Endpoints

**Person**

|Endpoint|Method|Description|
|---|---|---|
|`/api/persons/all{page}`|GET|Get a list of persons with pagination.|
|`/api/persons`|GET|Get a list of all persons.|
|`/api/persons/pattern={startWith}`|GET|Get a list of persons whose name starts with the specified pattern.|
|`/api/persons/{userId}`|GET|Get the person with the specified user ID.|
|`/api/persons`|POST|Add a new person.|
|`/api/persons/{id}`|DELETE|Delete the person with the specified ID.|
|`/api/persons/{userId}/doc_member`|GET|Get the document members associated with the person of the given user ID.|
|`/api/persons/{userId}/doc_payment`|GET|Get the document payments associated with the person of the given user ID.|
|`/api/persons/{userId}/class_education`|GET|Get the education information of the person with the given user ID.|
|`/api/persons/{userId}/workplace`|GET|Get the work places associated with the person of the given user ID.|
|`/api/persons/{id}`|PUT|Update the person with the specified ID.|

**Trade union**

|Endpoint|Method|Description|
|---|---|---|
|`/api/union/{unionId}`|GET|Get the trade union with the specified union ID.|
|`/api/union/{unionId}/docpayments`|GET|Get all document payments associated with the trade union of the given union ID.|
|`/api/union/{unionId}/members{pageNumber}`|GET|Get all active members associated with the trade union of the given union ID and page number.|
|`/api/union/{unionId}/members`|GET|Get all active members associated with the trade union of the given union ID.|
|`/api/union`|GET|Get a list of all trade unions.|
|`/api/union/{unionId}`|DELETE|Delete the trade union with the specified union ID.|
|`/api/union`|POST|Add a new trade union.|
|`/api/union/{id}`|PUT|Update the trade union with the specified ID.|

**Class education**

|Endpoint|Method|Description|
|---|---|---|
|`/api/education/{typeEducationId}/allpersons`|GET|Get a list of persons who are taking the specified type of education.|
|`/api/education`|GET|Get a list of all class education records.|
|`/api/education/{id}`|GET|Get the class education record with the specified ID.|

**Doc member**

| Endpoint                     | Method | Description                                                            |
| ---------------------------- | ------ | ---------------------------------------------------------------------- |
| `/api/union/docmember/all`   | GET    | Get a list of document members who have not finished their membership. |
| `/api/union/docmember/{num}` | GET    | Get the document member with the specified document number.            |

**Doc Payment**

| Endpoint                           | Method | Description                                                         |
| ---------------------------------- | ------ | ------------------------------------------------------------------- |
| `/api/docpayments`                 | GET    | Get a list of all document payments.                                |
| `/api/docpayments/{payId}`         | GET    | Get the document payment with the specified payment ID.             |
| `/api/docpayments/{payId}`         | DELETE | Delete the document payment with the specified payment ID.          |
| `/api/docpayments/{payId}`         | PUT    | Update the document payment with the specified payment ID.          |
| `/api/docpayments/count`           | GET    | Get the quantity of payments per trade union.                       |
| `/api/docpayments/count_statistic` | GET    | Get the quantity of payments that are not yet made per trade union. |


**Work Place**

|Endpoint|Method|Description|
|---|---|---|
|`/api/union/workplace/all`|GET|Get all work places.|
|`/api/union/workplace/{workPlaceId}`|GET|Get the work place with the specified ID.|


**User**

|Endpoint|Method|Description|
|---|---|---|
|`/api/user/findEmail`|GET|Get the user with the specified email.|
|`/api/user`|GET|Get the authenticated user with authorities.|
|`/api/user/permissions`|GET|Get the list of permissions for the specified role.|
|`/api/user/change_password`|POST|Change the password for the user with the specified email.|

**Authentication**

|Endpoint|Method|Description|
|---|---|---|
|`/api/auth/login`|POST|Authenticates a user with the provided credentials.|
|`/api/auth/logout`|POST|Logs out the currently authenticated user.|
|`/api/auth/register`|POST|Registers a new user with the provided registration details.|
|`/api/auth/refreshtoken`|POST|Refreshes an access token using a valid refresh token.|
|`/api/auth/validate`|POST|Validates if the given token is still valid for the user.|

## User Interface Overview


In this section, I provide an overview of the user interface of our application. Below are some screenshots and videos showcasing different pages and functionalities.

### Authorization

#### Login page with validation


https://github.com/borumv/Trade-Union-Application/assets/86356857/fc1170dd-a536-44aa-8be2-fa2084613fbd

#### Register page


https://github.com/borumv/Trade-Union-Application/assets/86356857/ef535d4b-da69-4427-ac6d-73b8d6288e61


### Working with tables

#### Different view with different roles

**Admin**

https://github.com/borumv/Trade-Union-Application/assets/86356857/1ca64a47-31a6-40c5-9b98-6915e427f707

**User**

https://github.com/borumv/Trade-Union-Application/assets/86356857/3e51d4ba-407c-4b50-90ea-bf4c9c06c85c

#### CRUD operations & Validation

https://github.com/borumv/Trade-Union-Application/assets/86356857/39f6c327-7d6f-4a68-ae7f-3d70cfb3bf2e




