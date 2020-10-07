FROM maven:3-openjdk-11
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml package

FROM adoptopenjdk/openjdk11:alpine-jre
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=/home/app/target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]