# Use OpenJDK 21 as base image
FROM openjdk:21-jdk-slim
# Install Maven
RUN apk add --no-cache maven

# Set the working directory
WORKDIR /app

# Copy the build files
COPY . .

# Build and package the application (if not built already)
RUN mvn clean package -DskipTests

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.war"]