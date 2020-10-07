FROM openjdk:8-jdk-alpine
COPY ./build/libs/todobackend.jar ./
ENTRYPOINT ["java"]
CMD ["-jar", "/todobackend.jar"]
EXPOSE 8080