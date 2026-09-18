FROM eclipse-temurin:21-jdk

RUN apt-get update \
    && apt-get install -y --no-install-recommends \
        git \
        maven \
        ca-certificates \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY target/PGDDemo-0.0.1-SNAPSHOT.jar app.jar

COPY docker/entrypoint.sh /app/entrypoint.sh
RUN chmod +x /app/entrypoint.sh

EXPOSE 9070

ENTRYPOINT ["/app/entrypoint.sh"]