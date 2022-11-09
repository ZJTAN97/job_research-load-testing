FROM maven:3.8.6-eclipse-temurin-17 as builder

RUN echo $JAVA_HOME && \
    echo $PATH

COPY cacerts $JAVA_HOME/lib/security

WORKDIR /usr/src/app
COPY . /usr/src/app
RUN mvn package

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /opt/app

COPY --from=builder /usr/src/app/target/load-testing-0.0.1-SNAPSHOT.jar /opt/app

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "load-testing-0.0.1-SNAPSHOT.jar"]