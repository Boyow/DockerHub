FROM httpd:latest
LABEL maintainer="markoz"
RUN apt-get update && apt-get install apache2 -y
EXPOSE 8080