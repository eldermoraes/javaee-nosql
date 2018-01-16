package br.com.eldermoraes.careerbuddy;

import br.com.eldermoraes.careerbuddy.cdi.CDIExtension;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(CDIExtension.class)
public class BuddyLoaderTest {


    @Inject
    private BuddyLoader buddyLoader;

    @Inject
    private Graph graph;
}