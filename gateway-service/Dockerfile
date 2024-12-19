FROM bitnami/java:1.8.432-7

WORKDIR /

COPY ./target/gateway-service-0.0.1-SNAPSHOT.jar /demo.jar

CMD ["java", "-jar", "demo.jar"]