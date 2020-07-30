FROM gcr.io/google-appengine/openjdk
COPY ./build/libs/todobackend.jar ./
ENTRYPOINT ["java"]
CMD ["-jar", "/todobackend.jar"]
EXPOSE 8080