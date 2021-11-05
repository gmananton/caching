FROM openjdk:11-jdk-slim
COPY ./build/libs/spring-caching-*.jar /app.jar

CMD ["java", "-Xmx256m", "-jar", "/app.jar"]

EXPOSE 8080