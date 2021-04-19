#!/bin/bash

cd apigateway
./gradlew clean assemble -Dorg.gradle.java.home="$1"
docker build -f Dockerfile -t apigateway:v1 .
cd ..

cd atendees
./gradlew clean assemble -Dorg.gradle.java.home="$1"
docker build -f Dockerfile -t atendees:v1 .
cd ..

cd orders
./gradlew clean assemble -Dorg.gradle.java.home="$1"
docker build -f Dockerfile -t orders:v1 .
cd ..

cd payments
./gradlew clean assemble -Dorg.gradle.java.home="$1"
docker build -f Dockerfile -t payments:v1 .
cd..

cd matches
./gradlew clean assemble -Dorg.gradle.java.home="$1"
docker build -f Dockerfile -t matches:v1 .
cd ..

docker-compose up
