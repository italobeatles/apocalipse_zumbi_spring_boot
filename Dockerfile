FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /workspace
COPY . /workspace
RUN mvn -q -DskipTests package

FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /workspace/target/apocalipse-zumbi-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/app.jar"]
