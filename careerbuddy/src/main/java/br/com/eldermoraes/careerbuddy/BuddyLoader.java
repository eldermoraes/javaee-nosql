/*
 * Copyright 2018 Elder Moreas and others
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.com.eldermoraes.careerbuddy;

import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.jnosql.artemis.Database;
import org.jnosql.artemis.DatabaseType;
import org.jnosql.artemis.graph.GraphTemplate;
import org.jnosql.artemis.graph.Transactional;

import javax.inject.Inject;
import java.util.function.Supplier;
import java.util.logging.Logger;

import static br.com.eldermoraes.careerbuddy.TechnologyLevel.ADVANCED;
import static br.com.eldermoraes.careerbuddy.TechnologyLevel.BEGINNER;
import static br.com.eldermoraes.careerbuddy.TechnologyLevel.INTERMEDIATE;

public class BuddyLoader {

    private static final Supplier<IllegalStateException> ENTITY_DOES_NOT_FOUND =
            () -> new IllegalStateException("Entity does not found");
    private static final Logger LOGGER = Logger.getLogger(BuddyLoader.class.getName());

    @Inject
    @Database(DatabaseType.GRAPH)
    private BuddyRepository buddyRepository;

    @Inject
    @Database(DatabaseType.GRAPH)
    private TechnologyRepository technologyRepository;

    @Inject
    @Database(DatabaseType.GRAPH)
    private CityRepository cityRepository;

    @Inject
    private BuddyService buddyService;

    @Inject
    private GraphTemplate template;

    @Inject
    private Graph graph;



    @Transactional
    public void clean() {

        graph.traversal().V().toList().forEach(Vertex::remove);
        graph.traversal().E().toList().forEach(Edge::remove);
    }


    @Transactional
    public void loadVertex() {

        if (!isElementEmpty()) {
            return;
        }
        buddyRepository.save(Buddy.of(Enums.Buddy.JOSE, 3_000D));
        buddyRepository.save(Buddy.of(Enums.Buddy.MARIO, 5_000D));
        buddyRepository.save(Buddy.of(Enums.Buddy.JOAO, 9_000D));
        buddyRepository.save(Buddy.of(Enums.Buddy.PEDRO, 14_000D));

        cityRepository.save(City.of(Enums.City.SAO_PAULO));
        cityRepository.save(City.of(Enums.City.BELO_HORIZONTE));
        cityRepository.save(City.of(Enums.City.SALVADOR));
        cityRepository.save(City.of(Enums.City.RIO_JANEIRO));
        cityRepository.save(City.of(Enums.City.CURITIBA));

        technologyRepository.save(Technology.of(Enums.Technology.JAVA));
        technologyRepository.save(Technology.of(Enums.Technology.NOSQL));
        technologyRepository.save(Technology.of(Enums.Technology.CLOUD));
        technologyRepository.save(Technology.of(Enums.Technology.CONTAINER));
        technologyRepository.save(Technology.of(Enums.Technology.GO));
    }

    @Transactional
    public void loadEdges() {


        if (isElementEmpty()) {
            throw new IllegalStateException("You cannot load");
        }

        if(!isEdgeEmpty()) {
            return;
        }

        Buddy jose = buddyRepository.findByName(Enums.Buddy.JOSE.getName()).orElseThrow(ENTITY_DOES_NOT_FOUND);
        Buddy mario = buddyRepository.findByName(Enums.Buddy.MARIO.getName()).orElseThrow(ENTITY_DOES_NOT_FOUND);
        Buddy joao = buddyRepository.findByName(Enums.Buddy.JOAO.getName()).orElseThrow(ENTITY_DOES_NOT_FOUND);
        Buddy pedro = buddyRepository.findByName(Enums.Buddy.PEDRO.getName()).orElseThrow(ENTITY_DOES_NOT_FOUND);


        City saopaulo = cityRepository.findByName(Enums.City.SAO_PAULO.getName()).orElseThrow(ENTITY_DOES_NOT_FOUND);
        City belohorizonte = cityRepository.findByName(Enums.City.BELO_HORIZONTE.getName()).orElseThrow(ENTITY_DOES_NOT_FOUND);
        City salvador = cityRepository.findByName(Enums.City.SALVADOR.getName()).orElseThrow(ENTITY_DOES_NOT_FOUND);

        Technology java = technologyRepository.findByName(Enums.Technology.JAVA.getName()).orElseThrow(ENTITY_DOES_NOT_FOUND);
        Technology nosql = technologyRepository.findByName(Enums.Technology.NOSQL.getName()).orElseThrow(ENTITY_DOES_NOT_FOUND);
        Technology cloud = technologyRepository.findByName(Enums.Technology.CLOUD.getName()).orElseThrow(ENTITY_DOES_NOT_FOUND);
        Technology container = technologyRepository.findByName(Enums.Technology.CONTAINER.getName()).orElseThrow(ENTITY_DOES_NOT_FOUND);
        Technology go = technologyRepository.findByName(Enums.Technology.GO.getName()).orElseThrow(ENTITY_DOES_NOT_FOUND);


        buddyService.work(jose, java, ADVANCED);
        buddyService.work(jose, nosql, BEGINNER);
        buddyService.work(jose, cloud, INTERMEDIATE);
        buddyService.work(jose, container, ADVANCED);
        buddyService.live(jose, saopaulo);


        buddyService.work(mario, go, ADVANCED);
        buddyService.work(mario, nosql, ADVANCED);
        buddyService.work(mario, cloud, BEGINNER);
        buddyService.work(mario, container, BEGINNER);
        buddyService.live(mario, salvador);

        buddyService.work(joao, java, INTERMEDIATE);
        buddyService.work(joao, cloud, ADVANCED);
        buddyService.work(joao, container, ADVANCED);
        buddyService.work(joao, go, BEGINNER);
        buddyService.live(joao, belohorizonte);


        buddyService.work(pedro, go, BEGINNER);
        buddyService.work(pedro, container, ADVANCED);
        buddyService.live(pedro, saopaulo);

    }

    private boolean isEdgeEmpty() {
        long edges = graph.traversal().E().count().tryNext().orElse(0L);
        LOGGER.info("Edges numbers in the database: " + edges);
        return edges == 0;
    }
    private boolean isElementEmpty() {
        long elements = graph.traversal().V().count().tryNext().orElse(0L);
        LOGGER.info("Elements numbers in the database: " + elements);
        return elements == 0;
    }
}
