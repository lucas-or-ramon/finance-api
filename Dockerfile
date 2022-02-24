FROM openjdk:11-jdk-slim-buster

COPY build/libs/finance-back-1.0.jar finance-back-1.0.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/finance-back-1.0.jar"]