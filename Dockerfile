FROM openjdk:8-jre

ENV TZ=Asia/Shanghai

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo '$TZ' > /etc/timezone

COPY ftp-server1.0.0.jar ftp-server.jar

VOLUME ["/opt/resource"]

ENTRYPOINT ["java","-jar","ftp-server.jar"]

EXPOSE 80
