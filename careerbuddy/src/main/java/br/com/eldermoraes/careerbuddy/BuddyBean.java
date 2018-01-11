package br.com.eldermoraes.careerbuddy;

import java.util.List;
import static java.util.stream.Collectors.toList;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import org.jnosql.artemis.graph.GraphTemplate;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.process.traversal.P;

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

    private final Jsonb jsonbBuilder = JsonbBuilder.create();
    

    public String getBuddiesByTechnology(Enums.Technology tech) {

        List<Buddy> list = graph.getTraversalVertex()
                .hasLabel(Enums.Entity.TECHNOLOGY.name())
                .has(Enums.Property.NAME.name().toLowerCase(), tech.name())
                .in(Enums.Edge.WORKS_WITH.name())
                .<Buddy>stream()
                .collect(toList());

        return jsonbBuilder.toJson(list);
    }

    public void loadData() {
        removeData();
        createEntities();
        createEdges();
    }

    private static Object getObject(Enums.Entity entity, Enums.Property identifier, Enum identifierValue, GraphTemplate graph) {
        return graph.getTraversalVertex().hasLabel(entity.name())
                .has(identifier.name().toLowerCase(), identifierValue.name())
                .next()
                .orElseThrow(() -> new IllegalStateException("Entity does not find"));
    }

    private void removeData() {

        graph.getTraversalEdge().has(Enums.Property.NAME.name(), Enums.Edge.WORKS_WITH.name()).stream().collect(toList()).forEach((o) -> {
            graph.delete(o.getId());
        });

        graph.getTraversalEdge().has(Enums.Property.NAME.name(), Enums.Edge.LIVES_IN.name()).stream().collect(toList()).forEach((o) -> {
            graph.delete(o.getId());
        });

        graph.getTraversalVertex().hasLabel(Enums.Entity.CITY.name()).<City>stream().collect(toList()).forEach((o) -> {
            graph.delete(o.getId());
        });

        graph.getTraversalVertex().hasLabel(Enums.Entity.TECHNOLOGY.name()).<Technology>stream().collect(toList()).forEach((o) -> {
            graph.delete(o.getId());
        });

        graph.getTraversalVertex().hasLabel(Enums.Entity.BUDDY.name()).<Buddy>stream().collect(toList()).forEach((o) -> {
            graph.delete(o.getId());
        });

        thinkerpop.tx().commit();
    }

    private void createEntities() {
        graph.insert(Buddy.of(Enums.Buddy.JOSE, 3_000D));
        graph.insert(Buddy.of(Enums.Buddy.MARIO, 5_000D));
        graph.insert(Buddy.of(Enums.Buddy.JOAO, 9_000D));
        graph.insert(Buddy.of(Enums.Buddy.PEDRO, 14_000D));

        graph.insert(City.of(Enums.City.SAO_PAULO));
        graph.insert(City.of(Enums.City.BELO_HORIZONTE));
        graph.insert(City.of(Enums.City.SALVADOR));
        graph.insert(City.of(Enums.City.RIO_JANEIRO));
        graph.insert(City.of(Enums.City.CURITIBA));

        graph.insert(Technology.of(Enums.Technology.JAVA));
        graph.insert(Technology.of(Enums.Technology.NOSQL));
        graph.insert(Technology.of(Enums.Technology.CLOUD));
        graph.insert(Technology.of(Enums.Technology.CONTAINER));
        graph.insert(Technology.of(Enums.Technology.GO));

        thinkerpop.tx().commit();
    }

    private void createEdges() {
        Buddy jose = (Buddy) getObject(Enums.Entity.BUDDY, Enums.Property.NAME, Enums.Buddy.JOSE, graph);
        Buddy mario = (Buddy) getObject(Enums.Entity.BUDDY, Enums.Property.NAME, Enums.Buddy.MARIO, graph);
        Buddy joao = (Buddy) getObject(Enums.Entity.BUDDY, Enums.Property.NAME, Enums.Buddy.JOAO, graph);
        Buddy pedro = (Buddy) getObject(Enums.Entity.BUDDY, Enums.Property.NAME, Enums.Buddy.PEDRO, graph);

        City saopaulo = (City) getObject(Enums.Entity.CITY, Enums.Property.NAME, Enums.City.SAO_PAULO, graph);
        City belohorizonte = (City) getObject(Enums.Entity.CITY, Enums.Property.NAME, Enums.City.BELO_HORIZONTE, graph);
        City salvador = (City) getObject(Enums.Entity.CITY, Enums.Property.NAME, Enums.City.SALVADOR, graph);
        //City riojaneiro = (City) getObject(CITY, NAME, RIO_JANEIRO, graph);
        //City curitiba = (City) getObject(CITY, NAME, CURITIBA, graph);

        Technology java = (Technology) getObject(Enums.Entity.TECHNOLOGY, Enums.Property.NAME, Enums.Technology.JAVA, graph);
        Technology nosql = (Technology) getObject(Enums.Entity.TECHNOLOGY, Enums.Property.NAME, Enums.Technology.NOSQL, graph);
        Technology cloud = (Technology) getObject(Enums.Entity.TECHNOLOGY, Enums.Property.NAME, Enums.Technology.CLOUD, graph);
        Technology container = (Technology) getObject(Enums.Entity.TECHNOLOGY, Enums.Property.NAME, Enums.Technology.CONTAINER, graph);
        Technology go = (Technology) getObject(Enums.Entity.TECHNOLOGY, Enums.Property.NAME, Enums.Technology.GO, graph);

        graph.edge(jose, Enums.Edge.WORKS_WITH.name(), java).add(Enums.Entity.LEVEL.name(), Enums.Level.ADVANCED.name());
        graph.edge(jose, Enums.Edge.WORKS_WITH.name(), nosql).add(Enums.Entity.LEVEL.name(), Enums.Level.BEGINNER.name());
        graph.edge(jose, Enums.Edge.WORKS_WITH.name(), cloud).add(Enums.Entity.LEVEL.name(), Enums.Level.INTERMEDIATE.name());
        graph.edge(jose, Enums.Edge.WORKS_WITH.name(), container).add(Enums.Entity.LEVEL.name(), Enums.Level.ADVANCED.name());
        graph.edge(jose, Enums.Edge.LIVES_IN.name(), saopaulo);

        graph.edge(mario, Enums.Edge.WORKS_WITH.name(), go).add(Enums.Entity.LEVEL.name(), Enums.Level.ADVANCED.name());
        graph.edge(mario, Enums.Edge.WORKS_WITH.name(), nosql).add(Enums.Entity.LEVEL.name(), Enums.Level.ADVANCED.name());
        graph.edge(mario, Enums.Edge.WORKS_WITH.name(), cloud).add(Enums.Entity.LEVEL.name(), Enums.Level.BEGINNER.name());
        graph.edge(mario, Enums.Edge.WORKS_WITH.name(), container).add(Enums.Entity.LEVEL.name(), Enums.Level.BEGINNER.name());
        graph.edge(mario, Enums.Edge.LIVES_IN.name(), salvador);

        graph.edge(joao, Enums.Edge.WORKS_WITH.name(), java).add(Enums.Entity.LEVEL.name(), Enums.Level.INTERMEDIATE.name());
        graph.edge(joao, Enums.Edge.WORKS_WITH.name(), cloud).add(Enums.Entity.LEVEL.name(), Enums.Level.ADVANCED.name());
        graph.edge(joao, Enums.Edge.WORKS_WITH.name(), container).add(Enums.Entity.LEVEL.name(), Enums.Level.ADVANCED.name());
        graph.edge(joao, Enums.Edge.WORKS_WITH.name(), go).add(Enums.Entity.LEVEL.name(), Enums.Level.BEGINNER.name());
        graph.edge(joao, Enums.Edge.LIVES_IN.name(), belohorizonte);

        graph.edge(pedro, Enums.Edge.WORKS_WITH.name(), go).add(Enums.Entity.LEVEL.name(), Enums.Level.BEGINNER.name());
        graph.edge(pedro, Enums.Edge.WORKS_WITH.name(), container).add(Enums.Entity.LEVEL.name(), Enums.Level.BEGINNER.name());
        graph.edge(pedro, Enums.Edge.LIVES_IN.name(), saopaulo);

        thinkerpop.tx().commit();
    }
}
