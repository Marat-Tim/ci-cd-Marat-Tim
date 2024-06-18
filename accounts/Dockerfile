FROM amazoncorretto:17

WORKDIR /app

COPY /target/accounts.jar /app/accounts.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "accounts.jar"]
