FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY build.gradle.kts .
COPY settings.gradle.kts .

COPY gradlew ./
COPY gradle ./gradle

RUN chmod +x ./gradlew

COPY src ./src

RUN ./gradlew build

EXPOSE 8080

CMD ["java", "-jar", "build/libs/fileHandler-1.0.0-SNAPSHOT.jar"]
