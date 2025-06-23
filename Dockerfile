# Use Maven with JDK 17 image
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set working directory inside container
WORKDIR /app

# Copy your entire project into container
COPY . .

# Build the project
RUN mvn clean package -DskipTests

# Use a lightweight JDK base image for running
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy the built jar file from previous stage
COPY --from=build /app/target/*jar-with-dependencies.jar app.jar

# Set the entry point
ENTRYPOINT ["java", "-jar", "app.jar"]
