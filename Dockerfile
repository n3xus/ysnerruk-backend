FROM adoptopenjdk/openjdk11:alpine-slim
VOLUME /tmp
ADD /target/api-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]