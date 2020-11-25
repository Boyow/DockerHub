FROM java:8
COPY . /
WORKDIR /etc
RUN javac DockerConnectMySQL.java
CMD ["java","-cp",".:/mysql-connector-java-8.0.21.jar","DockerConnectMySQL"]
