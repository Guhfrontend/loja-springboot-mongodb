FROM maven:3.8.5-openjdk-17 AS build

COPY src /app/src
COPY pom.xml /app

WORKDIR /app
RUN mvn clean install

FROM openjdk:8-jre-alpine

COPY --from=build /app/target/LojamongoApplication.jar /app/app.jar

WORKDIR /app

EXPOSE 8080

CMD ["java","-jar","app.jar"]

