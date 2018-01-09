package br.com.eldermoraes.careerbuddy;

import java.util.List;
import static java.util.stream.Collectors.toList;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import org.jnosql.artemis.graph.GraphTemplate;
import org.apache.tinkerpop.gremlin.structure.Graph;

/**
 *
 * @author eldermoraes
 */
@Stateless
public class BuddyBean {

    @Inject
    private GraphTemplate graph;

    @Inject
    private Graph thinkerpop;

    private final Jsonb jsonBBuilder = JsonbBuilder.create();

    private static final String CITY = "City";
    public static final String SAO_PAULO = "Sao Paulo";
    public static final String BELO_HORIZONTE = "Belo Horizonte";
    public static final String SALVADOR = "Salvador";
    public static final String RIO_JANEIRO = "Rio de Janeiro";
    public static final String CURITIBA = "Curitiba";

    private static final String TECHNOLOGY = "Technology";
    public static final String JAVA = "Java";
    public static final String NOSQL = "NoSQL";
    public static final String CLOUD = "Cloud";
    public static final String CONTAINER = "Containers";
    public static final String GO = "Go";

    private static final String BUDDY = "Buddy";
    public static final String JOSE = "Jose";
    public static final String MARIO = "Mario";
    public static final String JOAO = "Joao";
    public static final String PEDRO = "Pedro";

    private static final String NAME = "name";

    private static final String WORKS_WITH = "works with";
    private static final String LIVES_IN = "lives in";

    private static final String LEVEL = "level";
    private static final String BEGINNER = "beginner";
    private static final String INTERMEDIATE = "intermediate";
    private static final String ADVANCED = "advanced";

    public String getBuddiesByTechonology(String technology) {

        List<Buddy> list = graph.getTraversalVertex()
                .hasLabel(TECHNOLOGY)
                .has(NAME, technology)
                .in(WORKS_WITH)
                .<Buddy>stream()
                .collect(toList());

        return jsonBBuilder.toJson(list);
    }

    public String getBuddiesByTechonology(String tech1, String tech2) {

        List<Buddy> list = graph.getTraversalVertex()
                .hasLabel(TECHNOLOGY)
                .has(NAME, tech1)
                .in(WORKS_WITH)
                .<Buddy>stream()
                .collect(toList());

        return jsonBBuilder.toJson(list);
    }

    public void loadData() {
        removeData();
        createEntities();
        createEdges();
    }

    private static Object getObject(String entity, String identifier, String identifierValue, GraphTemplate graph) {
        return graph.getTraversalVertex().hasLabel(entity)
                .has(identifier, identifierValue)
                .next()
                .orElseThrow(() -> new IllegalStateException("Entity does not find"));
    }

    private void removeData() {
        
        graph.getTraversalEdge().has(NAME, WORKS_WITH).stream().collect(toList()).forEach((o) -> {
            graph.delete(o.getId());
        });

        graph.getTraversalEdge().has(NAME, LIVES_IN).stream().collect(toList()).forEach((o) -> {
            graph.delete(o.getId());
        });

        graph.getTraversalVertex().hasLabel(CITY).<City>stream().collect(toList()).forEach((o) -> {
            graph.delete(o.getId());
        });

        graph.getTraversalVertex().hasLabel(TECHNOLOGY).<Technology>stream().collect(toList()).forEach((o) -> {
            graph.delete(o.getId());
        });

        graph.getTraversalVertex().hasLabel(BUDDY).<Buddy>stream().collect(toList()).forEach((o) -> {
            graph.delete(o.getId());
        });

        thinkerpop.tx().commit();
    }

    private void createEntities() {
        graph.insert(Buddy.of(JOSE, 3_000D));
        graph.insert(Buddy.of(MARIO, 5_000D));
        graph.insert(Buddy.of(JOAO, 9_000D));
        graph.insert(Buddy.of(PEDRO, 14_000D));

        graph.insert(City.of(SAO_PAULO));
        graph.insert(City.of(BELO_HORIZONTE));
        graph.insert(City.of(SALVADOR));
        graph.insert(City.of(RIO_JANEIRO));
        graph.insert(City.of(CURITIBA));

        graph.insert(Technology.of(JAVA));
        graph.insert(Technology.of(NOSQL));
        graph.insert(Technology.of(CLOUD));
        graph.insert(Technology.of(CONTAINER));
        graph.insert(Technology.of(GO));

        thinkerpop.tx().commit();
    }

    private void createEdges() {
        Buddy jose = (Buddy) getObject(BUDDY, NAME, JOSE, graph);
        Buddy mario = (Buddy) getObject(BUDDY, NAME, MARIO, graph);
        Buddy joao = (Buddy) getObject(BUDDY, NAME, JOAO, graph);
        Buddy pedro = (Buddy) getObject(BUDDY, NAME, PEDRO, graph);

        City saopaulo = (City) getObject(CITY, NAME, SAO_PAULO, graph);
        City belohorizonte = (City) getObject(CITY, NAME, BELO_HORIZONTE, graph);
        City salvador = (City) getObject(CITY, NAME, SALVADOR, graph);
        //City riojaneiro = (City) getObject(CITY, NAME, RIO_JANEIRO, graph);
        //City curitiba = (City) getObject(CITY, NAME, CURITIBA, graph);

        Technology java = (Technology) getObject(TECHNOLOGY, NAME, JAVA, graph);
        Technology nosql = (Technology) getObject(TECHNOLOGY, NAME, NOSQL, graph);
        Technology cloud = (Technology) getObject(TECHNOLOGY, NAME, CLOUD, graph);
        Technology container = (Technology) getObject(TECHNOLOGY, NAME, CONTAINER, graph);
        Technology go = (Technology) getObject(TECHNOLOGY, NAME, GO, graph);

        graph.edge(jose, WORKS_WITH, java).add(LEVEL, ADVANCED);
        graph.edge(jose, WORKS_WITH, nosql).add(LEVEL, BEGINNER);
        graph.edge(jose, WORKS_WITH, cloud).add(LEVEL, INTERMEDIATE);
        graph.edge(jose, WORKS_WITH, container).add(LEVEL, ADVANCED);
        graph.edge(jose, LIVES_IN, saopaulo);

        graph.edge(mario, WORKS_WITH, go).add(LEVEL, ADVANCED);
        graph.edge(mario, WORKS_WITH, nosql).add(LEVEL, ADVANCED);
        graph.edge(mario, WORKS_WITH, cloud).add(LEVEL, BEGINNER);
        graph.edge(mario, WORKS_WITH, container).add(LEVEL, BEGINNER);
        graph.edge(mario, LIVES_IN, salvador);

        graph.edge(joao, WORKS_WITH, java).add(LEVEL, INTERMEDIATE);
        graph.edge(joao, WORKS_WITH, cloud).add(LEVEL, ADVANCED);
        graph.edge(joao, WORKS_WITH, container).add(LEVEL, ADVANCED);
        graph.edge(joao, WORKS_WITH, go).add(LEVEL, BEGINNER);
        graph.edge(joao, LIVES_IN, belohorizonte);

        graph.edge(pedro, WORKS_WITH, go).add(LEVEL, BEGINNER);
        graph.edge(pedro, WORKS_WITH, container).add(LEVEL, BEGINNER);
        graph.edge(pedro, LIVES_IN, saopaulo);

        thinkerpop.tx().commit();
    }
}
