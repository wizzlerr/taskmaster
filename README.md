# Taskmaster

Welcome to the TaskMaster application. This is a simple task management and notifications application that allows users
create and update tasks.

## Requirements

| #   | Requirement                                                                                                                                                                                 | Priority |
|-----|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------|
| R1  | As a user I want to be able to search, create, update and delete tasks of all types using the provided controller to schedule my events and reminders.                                      | Required |
| R2  | As a user I want to be able to specify the even type I want saved and supply the necessary information for the event (see domain models under `com.example.taskmaster.repository.domain`).  | Required |
| R3  | As a user I want to be able to authenticate on the system and have access to only tasks that belong to me                                                                                   | Required |
| R4  | As a user I want to receive configurable notifications about my task, I should be able to configure a notification which is at X time (min, hours, etc) before my event or at certain dates | Optional |

## Technical Information

- For `R4` implement the `synchronizer` module with a simple scheduler.
- For `R3` use whatever authentication method you would like.
- Set up and implement at least one test for the taskmaster module.
- The application uses an embedded mongo service which runs each time you start the service. If you'd like to use an external database then comment out the class annotations in classes
  under `com.example.taskmaster.app.config.*`
- This application was tested on Java1.8 and Apache Maven 3.6.3 and IntelliJ IDEA 2022.3

## Exercise Goals

What we are looking for is:

- Code quality and architectural/design decisions in implementing database, business logic and tests.
- Core understanding of the spring framework and the utilization of its libraries.
- Ability to identify and refactor code issues.

## Rules/Constrains

- You are allowed to refactor or change any of the code in this exercise as long as you don't deviate from the above requirements and the module structure.
- You can ask any questions and clarifications as necessary.

## Running and Compiling

```
mvn clean install
java -jar ./app/target/example-taskmaster-0.0.1-SNAPSHOT.jar
```

## Access OpenAPI Documentation

After running the application use the following URL to access the swagger documentation:
http://localhost:8080/swagger-ui/index.html#/