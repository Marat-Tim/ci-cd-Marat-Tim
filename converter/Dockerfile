FROM bellsoft/liberica-runtime-container:jdk-17-slim-musl

WORKDIR /app

COPY /target/converter.jar /app/converter.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "converter.jar"]
