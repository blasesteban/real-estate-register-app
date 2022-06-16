FROM maven:3.8.5-openjdk-17-slim
WORKDIR /app
EXPOSE 8080

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn package -DskipTests
CMD mvn spring-boot:run
