FROM amazoncorretto:17.0.6-al2023

WORKDIR /app

COPY ["hermes-consumer-main/build/libs/hermes-consumer-main-*.jar", "./hermes-consumer.jar"]
COPY ["docker/pipeline/entrypoint.sh", "/entrypoint.sh"]

RUN chmod +x /entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]