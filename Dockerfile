FROM openjdk:17-jdk-alpine

ARG DB_HOST
ARG DB_NAME
ARG DB_USER
ARG DB_PASSWORD

RUN mkdir /app
WORKDIR  /app

COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]