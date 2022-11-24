FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar emp-micro-service.jar
ENTRYPOINT ["java","-jar","/emp-micro-service.jar"]