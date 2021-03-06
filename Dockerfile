# Start with a base image containing Java runtime
FROM java:8

# Add Author info
LABEL maintainer="testjiwoo@gmail.com"

# Add a volume to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8000

# The application's jar file
#ARG JAR_FILE=target/blog-0.0.1-SNAPSHOT.jar
ARG JAR_FILE=target/blog-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} blog-springboot.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/blog-springboot.jar"]

