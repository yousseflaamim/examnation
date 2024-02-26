# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /usr/src/app

# Copy the application JAR file into the container at /usr/src/app
COPY target/rent-car.jar .

# Expose the port that the application runs on
EXPOSE 8080

# Specify the command to run your application
CMD ["java", "-jar", "rent-car.jar"]
