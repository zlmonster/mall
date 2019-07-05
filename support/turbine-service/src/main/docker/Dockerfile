FROM @docker.registry.name.prefix@/jdk8
#VOLUME /tmp
#VOLUME /app
ENV SERVICE_NAME turbine-service
ENV APP_HOME /app/microservice
RUN mkdir -p /data/logs
ADD ${SERVICE_NAME}.jar ${APP_HOME}/libs/${SERVICE_NAME}.jar
EXPOSE 17002
ENTRYPOINT java ${JAVA_OPTS} -jar ${APP_HOME}/libs/${SERVICE_NAME}.jar