FROM openjdk:17-alpine

COPY finance-api-1.0.jar finance-api-1.0.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/finance-api-1.0.jar"]