FROM openjdk:11

ADD target/mycms-backend-1.0.1.jar mycms-backend-1.0.1.jar

ENTRYPOINT ["java", "-jar","mycms-backend-1.0.1.jar"]

EXPOSE 50000