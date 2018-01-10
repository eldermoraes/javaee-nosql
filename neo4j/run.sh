docker rm -f neo4j || true

docker run -d \
	--name neo4j \
	-h neo4j:neo4j \
	-p 7474:7474 \
	-p 7687:7687 \
	-v $HOME/neo4j/data:/data \
	-e NEO4J_AUTH=none \
	neo4j
