FROM openjdk:8
ADD target/docker-bank-person-boot2.jar docker-bank-person-boot2.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "docker-bank-person-boot2.jar"]