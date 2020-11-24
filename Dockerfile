FROM openjdk:8

RUN apt-get update -y
RUN apt-get upgrade -y
COPY ./
RUN javac DockerConnectMySQL.java
CMD ["java","-cp",".:/mysql-connector-java-8.0.21.jar","DockerConnectMySQL"]