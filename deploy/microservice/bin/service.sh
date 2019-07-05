#!/bin/sh
# -----------------------------------------------------------------------------
# Control Script for the SpringBoot Application
#
# Environment Variable Prerequisites
#
#   APPLICATOIN_HOME   May point at your Application "build" directory.
#
#   JAVA_HOME       Must point at your Java Development Kit installation.
#                   Required to run the with the "debug" argument.
#
#   JRE_HOME        Must point at your Java Runtime installation.
#                   Defaults to JAVA_HOME if empty. If JRE_HOME and JAVA_HOME
#                   are both set, JRE_HOME is used.
#
#   JAVA_OPTS       (Optional) Java runtime options used when any command
#                   is executed.
# -----------------------------------------------------------------------------
# resolve links - $0 may be a softlink
PRG="$0"

while [ -h "$PRG" ]; do
  ls=`ls -ld "$PRG"`
  link=`expr "$ls" : '.*-> \(.*\)$'`
  if expr "$link" : '/.*' > /dev/null; then
    PRG="$link"
  else
    PRG=`dirname "$PRG"`/"$link"
  fi
done

# Get standard environment variables
PRGDIR=`dirname "$PRG"`

# Only set APPLICATOIN_HOME if not already set
[ -z "$APPLICATOIN_HOME" ] && APPLICATOIN_HOME=`cd "$PRGDIR/.." >/dev/null; pwd`

# Ensure that neither APPLICATOIN_HOME  contains a colon
# as this is used as the separator in the classpath and Java provides no
# mechanism for escaping if the same character appears in the path.
case $APPLICATOIN_HOME in
  *:*) echo "Using APPLICATOIN_HOME:   $APPLICATOIN_HOME";
       echo "Unable to start as APPLICATOIN_HOME contains a colon (:) character";
       exit 1;
esac

SERVICE_NAME=$1
SYSTEM_USER=`whoami`
APP_BIN_DIR="$APPLICATOIN_HOME/bin"
APP_LIBS_DIR="$APPLICATOIN_HOME/libs"
APP_LOGS_DIR="$APPLICATOIN_HOME/logs"
APPLICATION_BOOTSRAP="$APPLICATOIN_HOME/libs/$SERVICE_NAME.jar"
SERVICE_LOGS_DIR="$APP_LOGS_DIR/$SERVICE_NAME"
SERVICE_CATALINA_OUT="$SERVICE_LOGS_DIR/catalina.out"

SCRIPT_NAME=`basename $0`

if [ ! -d "$SERVICE_LOGS_DIR" ] ; then
    mkdir -p "$SERVICE_LOGS_DIR"
fi

# set jvm gc print
JAVA_OPTS="$JAVA_OPTS -XX:+PrintHeapAtGC -Xloggc:$SERVICE_LOGS_DIR/`date +"%Y%m%d%H%M%S"`_gc.log "
# set jvm heap dump dir
JAVA_OPTS="$JAVA_OPTS -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=$APP_LOGS_DIR"

validate()
{
    if [ ! -f "$APPLICATION_BOOTSRAP" ] ; then
        echo "$SERVICE_NAME is not exitsed in $APP_LIBS_DIR. Pleas, check and upload $SERVICE_NAME"
        exit 0
    fi
}

killadapter()
{
    # list pids of the application processes
    pids=`ps -fu  ${SYSTEM_USER} | grep "java" | grep $APPLICATION_BOOTSRAP | grep -v "grep" | awk '{print $2}'`
    if [ -n "$pids" ]; then
      kill -9 $pids
    else
        echo "$SERVICE_NAME process does not exist!"
    fi
}

start()
{
    source  "$APP_BIN_DIR/$SERVICE_NAME/setEnv.sh"
    # list pids of the application processes
    pids=`ps -fu ${SYSTEM_USER} | grep "java" | grep $APPLICATION_BOOTSRAP | grep -v "grep" | awk '{print $2}'`
    if [ -n "$pids" ]; then
       echo "$SERVICE_NAME already running."
       exit 0
    fi
    echo "starting $SERVICE_NAME at: `date`.."
    # start service application
    java $JAVA_OPTS -jar $APPLICATION_BOOTSRAP > "$SERVICE_CATALINA_OUT" 2>&1 &
#    tail -f "$SERVICE_CATALINA_OUT"
}

stop()
{
     echo "stopping $SERVICE_NAME at: `date`"
     killadapter
}


status()
{
    # list pids of the application processes
    pids=`ps -f U ${SYSTEM_USER} | grep "java" | grep $APPLICATION_BOOTSRAP | grep -v "grep"`

    if [ -n "$pids" ]; then
        echo "$SERVICE_NAME is running"
        exit 0
    fi
    echo $SERVICE_NAME is stopped
    exit 3
}
catlog()
{
    tail -f "$SERVICE_CATALINA_OUT"
}

restart()
{
    stop
    sleep 2
    start
}

usage()
{
  echo "Usage: $SCRIPT_NAME ( commands ... ) ( service ...)"
  echo "commands:"
  echo "start service:                    /bin/sh $SCRIPT_NAME [service-name] start"
  echo "stop service:                     /bin/sh $SCRIPT_NAME [service-name] stop"
  echo "restart service:                  /bin/sh $SCRIPT_NAME [service-name] restart"
  echo "query service status:             /bin/sh $SCRIPT_NAME [service-name] status"
  echo "cat service log:                  /bin/sh $SCRIPT_NAME [service-name] catlog"
  echo "query help:                       /bin/sh $SCRIPT_NAME -h|-help|--help|help"
  echo "--------------------------------------------------------------------------------"
  exit 0
}


case "$2" in
  start)
        validate
        start
        ;;
  stop)
        validate
        stop
        ;;
  restart)
        validate
        restart
        ;;
  status)
        validate
        status
        ;;
  catlog)
        validate
        catlog
        ;;
   -h|-help|--h|--help|help)
       usage
       exit 0
       ;;
  *)
        usage
        exit 1
esac

exit 0
