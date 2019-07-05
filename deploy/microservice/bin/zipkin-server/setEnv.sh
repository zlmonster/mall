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
JAVA_OPTS="$JAVA_OPTS -server -Xms1024M -Xmx1024M -Xss512K -Dfile.encoding=UTF-8 -XX:NewRatio=1 -XX:MetaspaceSize=512M -XX:MaxMetaspaceSize=1096M -Djava.awt.headless=true -d64"
JAVA_OPTS="$JAVA_OPTS -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:CMSFullGCsBeforeCompaction=0 -XX:+CMSClassUnloadingEnabled -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:SurvivorRatio=6 -XX:MaxTenuringThreshold=5 -XX:CMSInitiatingOccupancyFraction=70 -XX:SoftRefLRUPolicyMSPerMB=0 -XX:+PrintGCDateStamps -XX:+PrintGCDetails -Djava.net.preferIPv4Stack=true"

JAVA_OPTS="$JAVA_OPTS -Dserver.port=17003 -DKAFKA_BOOTSTRAP_SERVERS=192.168.85.2:9092 -DSTORAGE_TYPE=elasticsearch -DES_HOSTS=http://192.168.85.2:9200"
