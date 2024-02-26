FROM openjdk:8
EXPOSE 8082
ADD target/rent-car.jar  rent-car.jar
ENTRYPOINT ["java", "-jar", "rent-car.jar"]
