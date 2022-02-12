FROM openjdk:8-jdk

MAINTAINER suchen nan.su1314@gmail.com

ARG docker_artifact_file=spring-security-auth-server/target/spring-security-auth-server-1.0-SNAPSHOT.jar

COPY ${docker_artifact_file} /spring-security-auth-server.jar

RUN bash -c 'touch /springboot.jar'

EXPOSE 8081
ENTRYPOINT ["java", "-jar","/spring-security-auth-server.jar"]