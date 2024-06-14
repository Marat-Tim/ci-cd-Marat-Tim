FROM bellsoft/liberica-runtime-container:jdk-17-slim-musl

WORKDIR /app

COPY /target/accounts.jar /app/accounts.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "accounts.jar"]
