FROM maven:3.8.6-amazoncorretto-17 AS build

WORKDIR /project

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17-ea-10-jdk-slim

WORKDIR /app

COPY --from=build /project/target/*.jar /app/ciberpizza.jar

EXPOSE 8080

CMD ["java", "-jar", "ciberpizza.jar"]
