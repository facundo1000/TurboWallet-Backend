FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

ARG SECRET_KEY
ARG USER_GENERATOR

FROM openjdk:17.0.1-jdk-slim
ENV SECRET_KEY=${SECRET_KEY}
ENV USER_GENERATOR=${USER_GENERATOR}

COPY --from=build /app/target/alkywallet.jar alkywallet.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/alkywallet.jar"]