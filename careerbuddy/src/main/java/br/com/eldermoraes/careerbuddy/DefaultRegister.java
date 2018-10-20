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

import org.jnosql.artemis.EntityNotFoundException;
import org.jnosql.artemis.graph.GraphTemplate;
import org.jnosql.artemis.graph.Transactional;

import java.util.Optional;

import static java.util.Locale.US;

final class DefaultRegister implements Register, Register.RegisterCity, Register.RegisterWork, Register.RegisterLevel {

    private final GraphTemplate graphTemplate;

    private Buddy buddy;

    private Technology technology;

    DefaultRegister(GraphTemplate graphTemplate) {
        this.graphTemplate = graphTemplate;
    }

    @Override
    public RegisterCity buddy(BuddyDTO dto) {
        buddy = findByName(dto).orElse(dto.toEntity());
        buddy.setSalary(dto.getSalary());
        if (buddy.isNew()) {
            graphTemplate.insert(buddy);
        } else {
            graphTemplate.update(buddy);
        }
        graphTemplate.getTransaction().commit();

        buddy = findByName(dto)
                .orElseThrow(() -> new IllegalStateException("The Buddy does not found in the database: " +
                        dto.getName()));
        return this;
    }


    @Override
    public RegisterWork lives(String cityName) {
        City city = graphTemplate.getTraversalVertex().hasLabel(City.class)
                .has("name", cityName.toLowerCase(US)).<City>getSingleResult()
                .orElseThrow(() -> new EntityNotFoundException("City does not found with the name: " + cityName));

        graphTemplate.edge(buddy, Edges.LIVES, city);
        graphTemplate.getTransaction().commit();
        return this;
    }

    @Override
    public RegisterLevel works(String technologyName) {

        this.technology = graphTemplate.getTraversalVertex().hasLabel(Technology.class)
                .has("name", technologyName.toLowerCase(US)).<Technology>getSingleResult()
                .orElseThrow(
                        () -> new EntityNotFoundException("Technology does not found with the name: " + technologyName));

        return this;
    }

    @Override
    public void with(String level) {
        graphTemplate.edge(buddy, Edges.WORKS, technology).add(TechnologyLevel.EDGE_PROPERTY, level);
        graphTemplate.getTransaction().commit();
    }


    private Optional<Buddy> findByName(BuddyDTO buddy) {
        return graphTemplate.getTraversalVertex().hasLabel(Buddy.class)
                .has("name", buddy.getName().toLowerCase(US))
                .getSingleResult();
    }

}
