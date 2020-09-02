#!/bin/bash
echo ============================================================
echo Generate JAR
echo ============================================================
mvn clean package -DskipTests
echo ============================================================
echo BUILD and PUSH Project to Docker
echo ============================================================
docker rm -f app-invoice
docker build -t ivancondori/app-invoice:1.0 .
docker run -p 8081:8081 --name app-invoice --network aforo255-test -d ivancondori/app-invoice:1.0
echo ============================================================
echo End Process
echo ============================================================