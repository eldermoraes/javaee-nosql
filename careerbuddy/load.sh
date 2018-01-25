#!/bin/sh

#cities
curl -H "Content-Type: application/json" -X POST -d 'Sao Paulo' http://localhost:8080/careerbuddy/resource/cities/
curl -H "Content-Type: application/json" -X POST -d 'Salvador' http://localhost:8080/careerbuddy/resource/cities/
curl -H "Content-Type: application/json" -X POST -d 'Belo Horizonte' http://localhost:8080/careerbuddy/resource/cities/
curl -H "Content-Type: application/json" -X POST -d 'Rio de Janeiro' http://localhost:8080/careerbuddy/resource/cities/
curl -H "Content-Type: application/json" -X POST -d 'Curitiba' http://localhost:8080/careerbuddy/resource/cities/

#technologies
curl -H "Content-Type: application/json" -X POST -d 'Java' http://localhost:8080/careerbuddy/resource/technologies/
curl -H "Content-Type: application/json" -X POST -d 'NoSQL' http://localhost:8080/careerbuddy/resource/technologies/
curl -H "Content-Type: application/json" -X POST -d 'Cloud' http://localhost:8080/careerbuddy/resource/technologies/
curl -H "Content-Type: application/json" -X POST -d 'Container' http://localhost:8080/careerbuddy/resource/technologies/
curl -H "Content-Type: application/json" -X POST -d 'Golang' http://localhost:8080/careerbuddy/resource/technologies/

#buddies

curl -H "Content-Type: application/json" -X POST -d '{"name":"Jose","salary":3000.0}' http://localhost:8080/careerbuddy/resource/buddies/
curl -H "Content-Type: application/json" -X POST -d '{"name":"Mario","salary":5000.0}' http://localhost:8080/careerbuddy/resource/buddies/
curl -H "Content-Type: application/json" -X POST -d '{"name":"Joao","salary":9000.0}' http://localhost:8080/careerbuddy/resource/buddies/
curl -H "Content-Type: application/json" -X POST -d '{"name":"Pedro","salary":14000.0}' http://localhost:8080/careerbuddy/resource/buddies/

#lives

curl -H "Content-Type: application/json" -X PUT http://localhost:8080/careerbuddy/resource/buddies/mario/lives/salvador
curl -H "Content-Type: application/json" -X PUT http://localhost:8080/careerbuddy/resource/buddies/joao/lives/belohorizonte
curl -H "Content-Type: application/json" -X PUT http://localhost:8080/careerbuddy/resource/buddies/pedro/lives/saopaulo
curl -H "Content-Type: application/json" -X PUT http://localhost:8080/careerbuddy/resource/buddies/jose/lives/saopaulo

