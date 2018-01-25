# Careerbuddy

Recommendation system using Java EE 8 and Neo4J with JNoSQL.


![Neo4J Project](https://jnosql.github.io/img/logos/neo4j.png)

Neo4j is a graph database management system developed by Neo4j, Inc. Described by its developers as an ACID-compliant transactional database with native graph storage and processing, Neo4j is the most popular graph database according to DB-Engines ranking.


## Install Neo4J

### How To install


![Docker](https://www.docker.com/sites/default/files/horizontal_large.png)


1. Install docker: https://www.docker.com/
1. https://store.docker.com/images/neo4j
1. Run docker command
1. `docker run --publish=7474:7474 --publish=7687:7687 --volume=$HOME/neo4j/data:/data neo4j`
1. configure Neo4J http://localhost:7474


## Build
mvn clean package && docker build -t br.com.eldermoraes/careerbuddy .

## RUN

docker rm -f careerbuddy || true && docker run -d -p 8080:8080 -p 4848:4848 --name careerbuddy br.com.eldermoraes/careerbuddy


### Populate the database

```sbtshell
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
``` 


### Do query 


```sbtshell

curl http://localhost:8080/careerbuddy/resource/buddies/technologies/java
curl http://localhost:8080/careerbuddy/resource/buddies/technologies/cloud
curl http://localhost:8080/careerbuddy/resource/buddies/technologies/java/advanced
curl http://localhost:8080/careerbuddy/resource/buddies/cities/salvador
curl http://localhost:8080/careerbuddy/resource/buddies/cities/santos/technologies/java

``` 