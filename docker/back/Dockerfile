FROM tomcat:latest

WORKDIR /usr/local/tomcat

COPY ./docker/back/train-management-backend.war ./webapps/ROOT.war

EXPOSE 3000

CMD ["catalina.sh", "run"]