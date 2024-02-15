FROM bellsoft/liberica-runtime-container:jdk-17-slim-musl

WORKDIR /app

COPY /target/main.jar /app/main.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "main.jar"]
