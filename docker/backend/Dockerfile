FROM openjdk:11

WORKDIR clear

COPY target/mycms-backend-1.0.1.jar ./

EXPOSE 50000

ENTRYPOINT ["java", "-jar","mycms-backend-1.0.1.jar"]