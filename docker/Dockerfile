## IMPORTANTE
## Este Dockerfile es manejado por docker-maven-plugin
FROM openjdk:11-jre-slim-buster

RUN mkdir /config

VOLUME /tmp

# Copiamos la configuración por defecto para la IMAGEN
COPY *.properties /config

EXPOSE 8080

COPY *.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]