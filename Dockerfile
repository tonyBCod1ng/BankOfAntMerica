# Use OpenJDK 21 as base image
FROM --platform=$TARGETPLATFORM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the WAR file into the container
COPY target/*.war app.war

# Expose the port the app runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/app.war"]