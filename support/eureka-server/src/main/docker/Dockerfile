FROM @docker.registry.name.prefix@/jdk8
ENV SERVICE_NAME eureka-server
ENV APP_HOME /app/microservice
RUN mkdir -p /data/logs
ADD ${SERVICE_NAME}.jar ${APP_HOME}/libs/${SERVICE_NAME}.jar
WORKDIR /app
EXPOSE 18001
ENTRYPOINT java ${JAVA_OPTS} -jar ${APP_HOME}/libs/${SERVICE_NAME}.jar