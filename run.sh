#!/bin/sh

# Start the first process
java -jar -Dspring.profiles.active=dev /usr/local/tomcat/webapps/app.jar &
java -jar /usr/local/tomcat/webapps/app.war