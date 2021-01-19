FROM adoptopenjdk/maven-openjdk11

WORKDIR /usr/local/cucumber

COPY ./cucumber.zip .

RUN su root -c "apt-get update && apt-get install unzip"
RUN unzip ./cucumber.zip -d .
RUN rm -rf cucumber.zip

CMD ["mvn", "test", "-Dspring.profiles.active=docker"]