FROM openjdk:8
COPY ./Connect.java /etc
COPY ./mysql-connector-java-5.1.49.jar /etc
WORKDIR /etc
RUN javac Connect.java
CMD ["java","-cp","mysql-connector-java-5.1.49.jar:.","Connect"]
