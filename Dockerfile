FROM openjdk:11-jre-slim

WORKDIR /app

COPY . .

CMD ["java", "-jar", "my-application.jar"]
