FROM openjdk:11

WORKDIR /app

COPY target/public-library-0.0.1-SNAPSHOT.jar public-library-0.0.1-SNAPSHOT.jar

CMD [ "java", "-jar", "public-library-0.0.1-SNAPSHOT.jar"]