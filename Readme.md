# Weolbu Assignment

## Environment Setup

1. **Install JDK**: This project requires Java Development Kit (JDK) version 8 or above. You can download it from the [official Oracle website](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or install it using a package manager.

2. **Install Gradle**: This project uses Gradle as a build tool. You can download it from the [official Gradle website](https://gradle.org/install/) or install it using a package manager.

3. **Clone the repository**: Clone the repository to your local machine using the following command:

```bash
git clone https://github.com/matthew-weolbu/project-name.git
```

4. **Build the project**: Navigate to the project directory and build the project using Gradle:

```bash
cd project-name
./gradlew build
```

## Running the Application

1. **Start the application**: You can start the application using Gradle:

```bash
./gradlew bootRun
```

The application will start and by default can be accessed at http://localhost:8080.

## API Documentation

This project provides the following RESTful APIs:

1. **User Registration API**: This API is used to register a new user.

    - Endpoint: `/api/v1/users`
    - Method: `POST`
    - Request Body:
      ```json
      {
        "name": "John Doe",
        "email": "john.doe@example.com",
        "phoneNumber": "1234567890",
        "password": "password",
        "type": "STUDENT"
      }
      ```
    - Response: Returns the registered user details.

2. **Lecture Registration API**: This API is used to register a user for a lecture.

    - Endpoint: `/api/v1/registrations`
    - Method: `POST`
    - Request Body:
      ```json
      {
        "lectureId": 1
      }
      ```
    - Response: Returns the registration details.