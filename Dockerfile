FROM openjdk:22-jdk
WORKDIR /app
COPY target/processus-api-1.0.0.jar processus-api-1.0.0.jar
EXPOSE 8080
CMD ["java", "-jar", "processus-api-1.0.0.jar"]
