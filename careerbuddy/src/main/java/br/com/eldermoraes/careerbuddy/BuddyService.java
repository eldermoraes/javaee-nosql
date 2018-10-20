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

import org.apache.tinkerpop.gremlin.structure.Direction;
import org.jnosql.artemis.graph.EdgeEntity;
import org.jnosql.artemis.graph.GraphTemplate;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

@ApplicationScoped
public class BuddyService {

    @Inject
    private GraphTemplate graphTemplate;

    public List<Buddy> findByTechnology(String technology) throws NullPointerException {
        requireNonNull(technology, "technology is required");

        Stream<Buddy> buddies = graphTemplate.getTraversalVertex()
                .hasLabel(Technology.class)
                .has("name", technology)
                .in(Edges.WORKS).orderBy("name").asc().stream();

        return buddies.collect(Collectors.toList());
    }
    
    public List<Buddy> findByTechnology(String technology, TechnologyLevel level) throws NullPointerException {
        requireNonNull(technology, "technology is required");
        requireNonNull(level, "level is required");

        Stream<Buddy> buddies = graphTemplate.getTraversalVertex()
                .hasLabel(Technology.class)
                .has("name", technology)
                .inE(Edges.WORKS).has(TechnologyLevel.EDGE_PROPERTY, level.get())
                .outV().orderBy("name").asc().stream();

        return buddies.collect(Collectors.toList());
    }

    public List<Buddy> findByCity(String city) throws NullPointerException {
        requireNonNull(city, "city is required");

        Stream<Buddy> buddies = graphTemplate.getTraversalVertex()
                .hasLabel(City.class)
                .has("name", city)
                .in(Edges.LIVES)
                .orderBy("name").asc().stream();

        return buddies.collect(Collectors.toList());
    }

    public List<Buddy> findByTechnologyAndCity(String technology, String city) throws NullPointerException {
        requireNonNull(technology, "technology is required");
        requireNonNull(city, "city is required");

        Stream<Buddy> buddies = graphTemplate.getTraversalVertex()
                .hasLabel(Technology.class)
                .has("name", Name.of(technology).get())
                .in(Edges.WORKS)
                .filter(b -> graphTemplate.getEdges(b, Direction.OUT, Edges.LIVES).stream()
                        .<City>map(EdgeEntity::getIncoming)
                        .anyMatch(c -> c.equals(city))

                ).orderBy("name").asc().stream();

        return buddies.collect(Collectors.toList());
    }

    public void live(Buddy buddy, City city) throws NullPointerException {
        requireNonNull(buddy, "buddy is required");
        requireNonNull(city, "city is required");
        graphTemplate.edge(buddy, Edges.LIVES, city);
    }

    public void work(Buddy buddy, Technology technology) {
        requireNonNull(buddy, "buddy is required");
        requireNonNull(technology, "technology is required");

        graphTemplate.edge(buddy, Edges.WORKS, technology);
    }

    public void work(Buddy buddy, Technology technology, TechnologyLevel level) {
        requireNonNull(buddy, "buddy is required");
        requireNonNull(technology, "technology is required");
        requireNonNull(level, "level is required");

        EdgeEntity edge = graphTemplate.edge(buddy, Edges.WORKS, technology);
        edge.add(TechnologyLevel.EDGE_PROPERTY, level.get());
    }


    public Register getRegister() {
        return new DefaultRegister(graphTemplate);
    }




}
