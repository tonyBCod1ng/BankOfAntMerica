# Use a Debian-based OpenJDK image
FROM openjdk:21-jdk-slim

# Install Maven
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

# Set the working directory
WORKDIR /app

# Copy the build files
COPY . .

# Build and package the application (if not built already)
RUN mvn clean package -DskipTests

# Run the application
CMD ["java", "-jar", "BankOfAntMerica-0.0.1-SNAPSHOT.war"]