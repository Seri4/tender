FROM openjdk:11.0
VOLUME version=0.1
COPY ./tender=$version.jar /usr/app/tender.jar

WORKDIR /usr/app
USER root

RUN chgrp -R 0 /usr/app/ && chmod -R g=u /usr/app/; \
    chmod +x /usr/app/Starter.sh; \
    su -c 'rm -f /etc/localtime && ln -s /usr/share/zoneinfo/Europe/Moscow /etc/localtime'
ENV RUN_DEBUG=false;
EXPOSE 8080
EXPOSE 8000