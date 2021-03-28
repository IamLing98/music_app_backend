
FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=/build/libs/*.jar
COPY ${JAR_FILE} account-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/account-service-0.0.1-SNAPSHOT.jar"]