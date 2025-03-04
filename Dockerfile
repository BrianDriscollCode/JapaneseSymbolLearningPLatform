# Use an official OpenJDK runtime as the base image
FROM openjdk:17-jdk-slim as build

# Set the working directory
WORKDIR /app

# Copy the JAR file to the container
COPY target/demo-0.0.1-SNAPSHOT.jar /app/app.jar

# Copy the static Vue frontend files into the appropriate directory in the container
COPY src/main/resources/static /app/static

# Expose the port your application will run on
EXPOSE 8080

# Command to run the Spring Boot app
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

