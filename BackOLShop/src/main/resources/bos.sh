#!/bin/sh

java_bin=`which java`
java_min=512M
java_max=1024M
APP_NAME=bos
WORKINGDIR=/app/server/$APP_NAME
f_pid=$WORKINGDIR/$APP_NAME.pid
export LIB=lib
CLASSPATH="$LIB/$APP_NAME.jar:$LIB/:."
export CLASSPATH

cd $WORKINGDIR;

MAIN_CLASS_NAME=org.springframework.boot.loader.JarLauncher
SPRING_CONFIG_LOCATION=application.properties
LOGGING_CONFIG=logback.xml

export startDir=`pwd`

$java_bin -cp $CLASSPATH -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=6666,suspend=n -server -Duser.timezone=GMT -Ddefault.config.dir=$startDir -Dfile.encoding=ISO8859_1 -Xms${java_min} -Xmx${java_max} $MAIN_CLASS_NAME --spring.config.location=$SPRING_CONFIG_LOCATION --logging.config=$LOGGING_CONFIG > /dev/null &

PID=$!

if ! [ -z $PID ];then
  echo $PID > $f_pid
fi