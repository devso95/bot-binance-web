FROM openjdk:15-jdk-alpine
RUN apk --no-cache add curl

ARG WAR_FILE=target/*.war
ARG JAR_FILE=api/*.jar
RUN rm -rf /usr/local/tomcat/webapps/*
COPY ${WAR_FILE} /usr/local/tomcat/webapps/app.war
COPY ${JAR_FILE} /usr/local/tomcat/webapps/app.jar
COPY run.sh run.sh
EXPOSE 8288
CMD ./run.sh

#FROM adoptopenjdk/openjdk11
#EXPOSE 8288
#ARG WAR_FILE=target/*.war
#COPY ${WAR_FILE} docker_app.war
#ENTRYPOINT ["java","-jar","docker_app.war"]