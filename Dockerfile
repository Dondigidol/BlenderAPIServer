FROM openjdk:15-jdk-alpine

VOLUME /projects/BlenderApiServer/
WORKDIR /projects/BlenderApiServer/

ARG JAR_FILE=target/*.jar

COPY target/libs/ ./libs/
COPY ${JAR_FILE} "app.jar"
COPY config/ ./config/

EXPOSE 8080:8080

CMD java -jar app.jar