FROM bellsoft/liberica-runtime-container:jdk-17-slim-musl

WORKDIR /app

COPY Main.class /app/Main.class

ENTRYPOINT ["java", "Main"]
