FROM maven:3.8.5-openjdk-17-slim@sha256:502e781d39f0b40fbd02eb23f5b7663618b76ba52034da218c64e92f6c5647be as builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests
FROM openjdk:17-alpine3.14@sha256:4b6abae565492dbe9e7a894137c966a7485154238902f2f25e9dbd9784383d81
WORKDIR /app
EXPOSE 8080
COPY --from=builder /app/target/real-estate-register-0.0.1-SNAPSHOT.jar /app/real-estate-register.jar
CMD ["java", "-jar", "real-estate-register.jar"]