FROM openjdk:8-jdk-alpine

ENV PORT 8080
ENV SPRING_PROFILES_ACTIVE local
ENV JAVA_OPTS "-Xmx1024m"

COPY build/libs/*.jar spring-webflux-with-kotlin-1.0.0.jar

EXPOSE $PORT

ENTRYPOINT ["java", "-jar", "spring-webflux-with-kotlin-1.0.0.jar"]