FROM gcr.io/google-appengine/openjdk
COPY build/libs/backend.jar ./
ENTRYPOINT ["java"]
CMD ["-jar", "/backend.jar"]
EXPOSE 8080