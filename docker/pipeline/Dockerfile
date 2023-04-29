FROM eclipse-temurin:17.0.6_10-jdk-jammy

WORKDIR /app

COPY ["hermes-consumer-main/build/libs/hermes-consumer-main-*.jar", "./hermes-consumer.jar"]
COPY ["docker/pipeline/entrypoint.sh", "/entrypoint.sh"]

RUN chmod +x /entrypoint.sh

STOPSIGNAL SIGTERM
ENTRYPOINT ["/entrypoint.sh"]