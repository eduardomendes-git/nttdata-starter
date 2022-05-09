FROM openjdk:11

WORKDIR /app

COPY target/public-library-0.0.1-SNAPSHOT.jar public-library-0.0.1-SNAPSHOT.jar

CMD [ "java", "-jar", "public-library-0.0.1-SNAPSHOT.jar"]

# Run this command to create the image: '$docker build .'
# Run this command to create and run a container based on this image: '$docker run -p 8080:8080 <image-id>'