#!/bin/bash

yes | docker image prune

cd gateway || exit
./gradlew clean assemble -Dorg.gradle.java.home="$1"
docker build -f Dockerfile -t gateway:v1 .
cd ..

cd atendees || exit
./gradlew clean assemble -Dorg.gradle.java.home="$1"
docker build -f Dockerfile -t atendees:v1 .
cd ..

cd orders || exit
./gradlew clean assemble -Dorg.gradle.java.home="$1"
docker build -f Dockerfile -t orders:v1 .
cd ..

cd payments || exit
./gradlew clean assemble -Dorg.gradle.java.home="$1"
docker build -f Dockerfile -t payments:v1 .
cd ..

cd matches || exit
./gradlew clean assemble -Dorg.gradle.java.home="$1"
docker build -f Dockerfile -t matches:v1 .
cd ..

cd notifications || exit
./gradlew clean assemble -Dorg.gradle.java.home="$1"
docker build -f Dockerfile -t notifications:v1 .
cd ..

docker-compose up
