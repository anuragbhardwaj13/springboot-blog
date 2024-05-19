# Stage 1: Build
FROM ubuntu:latest AS build
RUN apt-get update
RUN apt-get install -y openjdk-21-jdk maven
COPY . .
RUN ./mvnw clean package -DskipTests

# Stage 2: Run
FROM openjdk:21-jdk-slim
EXPOSE 8080
COPY --from=build /target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar","--spring.data.mongodb.uri=${SPRING_DATA_MONGODB_URI}","--blogapp.jwt.jwtSecret=${BLOGAPP_JWT_JWTSECRET}","--blogapp.jwt.jwtExpirationMs=${BLOGAPP_JWT_JWTEXPIRATIONMS}"]
