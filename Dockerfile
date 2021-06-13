FROM openjdk:11
ADD target/pdsmanagement-app.jar pdsmanagement-app.jar
EXPOSE 8181
ENTRYPOINT ["java","-jar","pdsmanagement-app.jar"]