FROM eclipse-temurin:19
EXPOSE 8080
ADD target/spring-redis-demo.jar spring-redis-demo.jar
ENTRYPOINT ["java","-jar","/spring-redis-demo.jar"]