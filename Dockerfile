FROM ghcr.io/graalvm/graalvm-ce:21.2.0
COPY target/pdsmanagement-app.jar pdsmanagement-app.jar
EXPOSE 8181
ENTRYPOINT ["java","-jar","pdsmanagement-app.jar"]
