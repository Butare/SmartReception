FROM openjdk:9
VOLUME /tmp
EXPOSE 8080
COPY build/libs/SmartReception.jar SmartReception.jar
ENTRYPOINT ["java", "-jar", "/SmartReception.jar"]
