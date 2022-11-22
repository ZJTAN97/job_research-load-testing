FROM eclipse-temurin:17.0.5_8-jre-ubi9-minimal

WORKDIR /gatling

COPY . .

CMD ["./mvnw", "clean", "verify"]

CMD ["./mvnw", "gatling:test"]