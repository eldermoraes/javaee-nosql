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