package br.com.eldermoraes.careerbuddy.driver;

import org.apache.tinkerpop.gremlin.neo4j.structure.Neo4jGraph;
import org.apache.tinkerpop.gremlin.structure.Graph;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.interceptor.Interceptor;
import java.io.File;

@ApplicationScoped
@Alternative
@Priority(Interceptor.Priority.APPLICATION+10)
public class EmbeddedSupplier implements GraphSupplier {

    private Graph graph = Neo4jGraph.open(new File("").getAbsolutePath() + "/target/jnosql-graph");

    @Override
    public Graph get() {
        return graph;
    }
}
