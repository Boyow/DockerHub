FROM java:8
COPY ./Main.java /etc
COPY ./mysql-connector-java-5.1.49.jar /etc
WORKDIR /etc
RUN javac Main.java
CMD ["java","-cp",".:/mysql-connector-java-5.1.49.jar","Main"]
