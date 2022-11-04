# TO INCLUDE BUILD SCRIPT IN INTERNAL NETWORK

FROM maven:3.8.6-jdk-11
WORKDIR /usr/src/app
COPY ./load-testing/target/load-testing-0.0.1-SNAPSHOT.jar /usr/src/app

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "load-testing-0.0.1-SNAPSHOT.jar"]