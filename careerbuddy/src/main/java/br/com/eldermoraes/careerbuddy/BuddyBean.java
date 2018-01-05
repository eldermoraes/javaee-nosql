package br.com.eldermoraes.careerbuddy;

import javax.ejb.Init;
import javax.ejb.Stateless;
import javax.inject.Inject;
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
    
    private static final String CITY = "City";
    private static final String SAO_PAULO = "Sao Paulo";
    private static final String BELO_HORIZONTE = "Belo Horizonte";
    private static final String SALVADOR = "Salvador";
    private static final String RIO_JANEIRO = "Rio de Janeiro";
    private static final String CURITIBA = "Curitiba";

    private static final String TECHNOLOGY = "Technology";
    private static final String JAVA = "Java";
    private static final String NOSQL = "NoSQL";
    private static final String CLOUD = "Cloud";
    private static final String CONTAINER = "Containers";
    private static final String GO = "Go";
    
    private static final String BUDDY = "Buddy";
    private static final String JOSE = "Jose";
    private static final String MARIO = "Mario";
    private static final String JOAO = "Joao";
    private static final String PEDRO = "Pedro";
    
    private static final String NAME = "name";
    
    private static final String WORKS_WITH = "works with";
    private static final String LIVES_IN = "lives in";
    
    private static final String LEVEL = "level";
    private static final String BEGINNER = "beginner";
    private static final String INTERMEDIATE = "intermediate";
    private static final String ADVANCED = "advanced";
   
   
    public String getBuddies() {
        
        loadData();
        
        Buddy jose = (Buddy) getValue(BUDDY, NAME, JOSE, graph);
        Buddy mario = (Buddy) getValue(BUDDY, NAME, MARIO, graph);
        Buddy joao = (Buddy) getValue(BUDDY, NAME, JOAO, graph);
        Buddy pedro = (Buddy) getValue(BUDDY, NAME, PEDRO, graph);
        
        City saopaulo = (City) getValue(CITY, NAME, SAO_PAULO, graph);
        City belohorizonte = (City) getValue(CITY, NAME, BELO_HORIZONTE, graph);
        City salvador = (City) getValue(CITY, NAME, SALVADOR, graph);
        City riojaneiro = (City) getValue(CITY, NAME, RIO_JANEIRO, graph);
        City curitiba = (City) getValue(CITY, NAME, CURITIBA, graph);
        
        Technology java = (Technology) getValue(TECHNOLOGY, NAME, JAVA, graph);
        Technology nosql = (Technology) getValue(TECHNOLOGY, NAME, NOSQL, graph);
        Technology cloud = (Technology) getValue(TECHNOLOGY, NAME, CLOUD, graph);
        Technology container = (Technology) getValue(TECHNOLOGY, NAME, CONTAINER, graph);
        Technology go = (Technology) getValue(TECHNOLOGY, NAME, GO, graph);
        
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
        
        return "ainda vazio";
    }
    
    private void loadData(){
        
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
    
    private static Object getValue(String entity, String identifier, String identifierValue, GraphTemplate graph){
        return graph.getTraversalVertex().hasLabel(entity)
                .has(identifier, identifierValue)
                .<City>next()
                .orElseThrow(() -> new IllegalStateException("Entity does not find"));
    }
}
