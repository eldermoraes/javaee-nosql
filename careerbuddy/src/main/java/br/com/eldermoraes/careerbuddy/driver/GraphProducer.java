package br.com.eldermoraes.careerbuddy.driver;

import org.apache.tinkerpop.gremlin.structure.Graph;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

public class GraphProducer {

    @Inject
    private GraphSupplier graphSupplier;

    @Produces
    public Graph getGraph() {
        return graphSupplier.get();
    }
}
