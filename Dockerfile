FROM ghcr.io/graalvm/graalvm-ce:21.2.0
WORKDIR /app
COPY . /app
RUN mvn clean install
COPY target/pdsmanagement-app.jar pdsmanagement-app.jar
EXPOSE 8181
ENTRYPOINT ["java","-jar","pdsmanagement-app.jar"]
