FROM eclipse-temurin:19
EXPOSE 8080
ADD target/starwar-api.jar starwar-api.jar
ENTRYPOINT ["java","-jar","/starwar-api.jar"]