FROM openjdk:8
ADD examen-0.0.1.war examen-0.0.1.war
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "examen-0.0.1.war"]