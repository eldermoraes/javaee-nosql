package br.com.eldermoraes.careerbuddy;

import br.com.eldermoraes.careerbuddy.cdi.CDIExtension;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.inject.Inject;

@ExtendWith(CDIExtension.class)
public class BuddyLoaderTest {


    @Inject
    private BuddyLoader buddyLoader;

    @Inject
    private Graph graph;



    @Test
    public void shouldCreateEdges() {
        buddyLoader.loadVertex();
        Assertions.assertEquals(14, graph.traversal().V().toList().size());
    }
}