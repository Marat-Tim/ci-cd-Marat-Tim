FROM amazoncorretto:17

WORKDIR /app

COPY /target/converter.jar /app/converter.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "converter.jar"]
