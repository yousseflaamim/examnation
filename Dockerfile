# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jre-slim
EXPOSE 8082
ADD target/rent-car.jar rent-car.jar
# Set the working directory in the container




# Specify the command to run your application
ENTRYPOINT ["java", "-jar", "rent-car.jar"]
