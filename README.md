Use a base image with OpenJDK and a specific Java version
```
FROM openjdk:17-jdk-alpine
```
Set the working directory inside the container
```
WORKDIR /app
```

Copy the build.gradle.kts and gradlew files to the working directory
```
COPY build.gradle.kts .
COPY gradlew .
```
Copy the src directory to the working directory
```
COPY src ./src
```
Run command to initialize the Gradle wrapper
```
RUN ./gradlew
```

Run command to build the project and generate the JAR
```
RUN ./gradlew build
```

Expose port 8080 for accessing the Spring Boot application
```
EXPOSE 8080
```
Command to run the application when the container starts
```
CMD ["java", "-jar", "build/libs/your-application-name.jar"]
```
