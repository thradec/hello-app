FROM openjdk:8
ADD hello-app-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "/app.jar"]
HEALTHCHECK CMD curl --fail localhost:8090/dev/health || exit 1