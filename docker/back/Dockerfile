FROM tomcat:latest

WORKDIR /usr/local/tomcat

COPY ./tain-management-backend.war ./webapps/.

EXPOSE 8080
EXPOSE 8000

CMD ["catalina.sh", "run"]