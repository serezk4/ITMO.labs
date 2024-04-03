# Define the build stage with Gradle
FROM gradle:jdk21-alpine as build

# Set working directory for the build stage
WORKDIR /workspace/app

# Copy Gradle configuration files and source code into the image
COPY build.gradle settings.gradle ./
COPY src src

# No need to set executable permissions for gradlew when using the Gradle image

# Build the application
RUN gradle bootJar --no-daemon

# Define the final stage to run the application
FROM openjdk:21-jdk-slim

# Copy the built JAR from the build stage into the final image
COPY --from=build /workspace/app/build/libs/*.jar app.jar

# Specify the entry point to run the JAR
ENTRYPOINT ["java","-jar","/app.jar"]
