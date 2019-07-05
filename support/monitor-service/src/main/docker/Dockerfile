FROM @docker.registry.name.prefix@/jdk8
#VOLUME /tmp
#VOLUME /app
ENV SERVICE_NAME monitor-service
ENV APP_HOME /app/microservice
RUN mkdir -p /data/logs
ADD ${SERVICE_NAME}.jar ${APP_HOME}/libs/${SERVICE_NAME}.jar
EXPOSE 17004
ENTRYPOINT java ${JAVA_OPTS} -jar ${APP_HOME}/libs/${SERVICE_NAME}.jar