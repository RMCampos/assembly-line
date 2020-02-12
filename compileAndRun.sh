#!/bin/bash

mvn package

if [ "$?" -ne 0 ]; then
  echo "Fail at compilation"
  exit 1
fi

cd target/
java -jar assembly-line-1.0-SNAPSHOT.jar < ../input.txt