#!/bin/sh

#cities
curl -H "Content-Type: application/json" -X POST -d 'Santos' http://localhost:8080/careerbuddy/resource/cities/
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
curl -H "Content-Type: application/json" -X PUT http://localhost:8080/careerbuddy/resource/buddies/joao/lives/curitiba
curl -H "Content-Type: application/json" -X PUT http://localhost:8080/careerbuddy/resource/buddies/pedro/lives/santos
curl -H "Content-Type: application/json" -X PUT http://localhost:8080/careerbuddy/resource/buddies/jose/lives/santos

#works

curl -H "Content-Type: application/json" -X PUT http://localhost:8080/careerbuddy/resource/buddies/jose/works/java/advanced
curl -H "Content-Type: application/json" -X PUT http://localhost:8080/careerbuddy/resource/buddies/jose/works/nosql/beginner
curl -H "Content-Type: application/json" -X PUT http://localhost:8080/careerbuddy/resource/buddies/jose/works/cloud/intermediate
curl -H "Content-Type: application/json" -X PUT http://localhost:8080/careerbuddy/resource/buddies/jose/works/container/advanced

curl -H "Content-Type: application/json" -X PUT http://localhost:8080/careerbuddy/resource/buddies/mario/works/golang/advanced
curl -H "Content-Type: application/json" -X PUT http://localhost:8080/careerbuddy/resource/buddies/mario/works/nosql/advanced
curl -H "Content-Type: application/json" -X PUT http://localhost:8080/careerbuddy/resource/buddies/mario/works/cloud/beginner
curl -H "Content-Type: application/json" -X PUT http://localhost:8080/careerbuddy/resource/buddies/mario/works/container/beginner

curl -H "Content-Type: application/json" -X PUT http://localhost:8080/careerbuddy/resource/buddies/joao/works/java/intermediate
curl -H "Content-Type: application/json" -X PUT http://localhost:8080/careerbuddy/resource/buddies/joao/works/cloud/advanced
curl -H "Content-Type: application/json" -X PUT http://localhost:8080/careerbuddy/resource/buddies/joao/works/container/advanced
curl -H "Content-Type: application/json" -X PUT http://localhost:8080/careerbuddy/resource/buddies/joao/works/golang/beginner

curl -H "Content-Type: application/json" -X PUT http://localhost:8080/careerbuddy/resource/buddies/pedro/works/golang/beginner
curl -H "Content-Type: application/json" -X PUT http://localhost:8080/careerbuddy/resource/buddies/pedro/works/container/advanced

