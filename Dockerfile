FROM openjdk:11-jdk-slim-buster

EXPOSE 8080

ADD build/libs/finance-back-1.0.jar finance-back-1.0.jar

ENTRYPOINT ["java","-jar","/finance-back-1.0.jar"]