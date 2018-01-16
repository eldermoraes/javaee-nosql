package br.com.eldermoraes.careerbuddy.driver;

import com.steelbridgelabs.oss.neo4j.structure.Neo4JElementIdProvider;
import com.steelbridgelabs.oss.neo4j.structure.Neo4JGraph;
import com.steelbridgelabs.oss.neo4j.structure.providers.Neo4JNativeElementIdProvider;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.jnosql.artemis.ConfigurationUnit;
import org.neo4j.driver.v1.Driver;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

public class DefaultGraphSupplier implements GraphSupplier {

    private Neo4JGraph graph;

    @Inject
    @ConfigurationUnit
    private Instance<Driver> driver;



    @PostConstruct
    public void init() {
        Neo4JElementIdProvider<?> vertexIdProvider = new Neo4JNativeElementIdProvider();
        Neo4JElementIdProvider<?> edgeIdProvider = new Neo4JNativeElementIdProvider();
        this.graph = new Neo4JGraph(driver.get(), vertexIdProvider, edgeIdProvider);
        graph.setProfilerEnabled(true);
    }


    public Graph get() {
        return graph;
    }

}
