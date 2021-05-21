FROM maven:3.8.1-adoptopenjdk-11 AS MAVEN_BUILD

MAINTAINER hitesh

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package

FROM gcr.io/distroless/java:11
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/bms-producer-1.0.0.jar /app/
EXPOSE 8090
EXPOSE 8089
CMD ["bms-producer-1.0.0.jar"]
