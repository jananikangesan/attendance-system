# Attendance System

This project is an implementation of an attendance management system for a school. It consists of a backend server developed using Java and Spring Boot, and a frontend client built using HTML and JavaScript,with Bootstrap integrated via npm for styling. The system includes functionality for user login, JWT token-based authentication, and a basic interface for Home page.

## Project Structure

The project is divided into the following directories:

- **server/**: Contains the backend code, including the Spring Boot application and REST APIs.
- **client/**: Contains the frontend code with HTML, JavaScript, and styles.
- **docs/**: Contains screenshots for the project.

## Features

- **Login page**: Allows users to log in using a username and password.
- **Home page**: Displays a welcome message and provides a logout button.
- **JWT Authentication**: The server issues a JWT token after successful login, and the client uses this token for authentication.
- **Redirects**: 
  - Redirects to the home page after a successful login.
  - Redirects to the login page if the user is not logged in.
  - Redirects to the login page on logout.

## Setup Instructions

Follow these steps to set up and run the project:

### 1. Clone the Repository
- git clone <repository_link>
- cd attendance-system
### 2. Set Up the Backend (Server)
- Navigate to the server folder: cd server
- Build the project using Maven: mvn clean install
- Run the Spring Boot application: mvn spring-boot:run
- The server application will be run at http://localhost:8082
### 3. Set Up the Frontend (Client)
- Navigate to the client folder :cd client
- Install Bootstrap using npm: npm install bootstrap
- Run the frontend client using http-server: npx http-server -p 3000
- The client application will be available at http://localhost:3000
### 4. Testing Credentials
The test username and password are stored in the user.txt file located in the resources folder of the backend project. The format inside the file is admin=admin123. where,
* username => admin
* password => admin123


