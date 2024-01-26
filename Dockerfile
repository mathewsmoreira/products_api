FROM maven:3.9.6-eclipse-temurin-17-focal as build
COPY /src /app/src
COPY /pom.xml /app
RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip

FROM eclipse-temurin:17.0.10_7-jdk-focal
EXPOSE 8080
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT [ "java", "-jar","/app.jar" ]

