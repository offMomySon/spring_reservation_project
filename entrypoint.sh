#!/bin/bash

java -jar -Xms512m -Xmx512m -Dspring.profiles.active=prod jihun.war &
status=$?
if [ $status -ne 0 ]; then
  echo "Failed to start my_first_process: $status"
  exit $status
fi

java -jar -Xms512m -Xmx512m -Dspring.profiles.active=prod alarmServer.jar &
status=$?
if [ $status -ne 0 ]; then
  echo "Failed to start my_second_process: $status"
  exit $status
fi

java -jar -Xms512m -Xmx512m -Dspring.profiles.active=prod reflectPromotionServer.jar &
status=$?
if [ $status -ne 0 ]; then
  echo "Failed to start my_second_process: $status"
  exit $status
fi

while sleep 60; do
  ps aux | grep jihun.war | grep -q -v grep
  PROCESS_1_STATUS=$?
  ps aux | grep alarmServer.jar | grep -q -v grep
  PROCESS_2_STATUS=$?
  ps aux | grep reflectPromotionServer.jar | grep -q -v grep
  PROCESS_3_STATUS=$?

  if [ $PROCESS_1_STATUS -ne 0 -o $PROCESS_2_STATUS -ne 0 -o $PROCESS_3_STATUS -ne 0 ]; then
    echo "One of the processes has already exited."
    exit 1
  fi
done

##!/bin/bash
#java -jar -Xms512m -Xmx512m -Dspring.profiles.active=prod jihun.war
#java -jar -Xms512m -Xmx512m -Dspring.profiles.active=prod alarmServer.jar
#java -jar -Xms512m -Xmx512m -Dspring.profiles.active=prod reflectPromotionServer.jar
#
