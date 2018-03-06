#!/bin/sh
mvn clean package && docker build -t eldermoraes/careerbuddy .
docker rm -f careerbuddy || true && docker run -d -p 8080:8080 -p 4848:4848 --link neo4j:neo4j --name careerbuddy eldermoraes/careerbuddy
