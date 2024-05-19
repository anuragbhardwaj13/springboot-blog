FROM ubuntu:latest AS build
RUN apt-get update
RUN apt-get install openjdk-21-jdk -y
COPY . .
RUN ./mvnw bootJar --no-daemon

FROM openjdk:21-jdk-slim
EXPOSE 8080
COPY --from=build target/target/blogapp-1.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar","--spring.data.mongodb.uri=${SPRING_DATA_MONGODB_URI}","--blogapp.jwt.jwtSecret=${BLOGAPP_JWT_JWTSECRET}","--blogapp.jwt.jwtExpirationMs=${BLOGAPP_JWT_JWTEXPIRATIONMS}"]
