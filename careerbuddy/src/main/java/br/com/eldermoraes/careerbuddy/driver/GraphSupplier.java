package br.com.eldermoraes.careerbuddy.driver;

import org.apache.tinkerpop.gremlin.structure.Graph;

public interface GraphSupplier {

    Graph get();
}
