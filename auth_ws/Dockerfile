FROM openjdk:17-jdk-slim
ARG JAR_FILE="build/libs/auth_ws-0.0.1.jar"
COPY ${JAR_FILE} "/opt/app/auth-ws.jar"
WORKDIR /opt/app
ENTRYPOINT ["java","-jar","/opt/app/auth-ws.jar"]
EXPOSE 9003