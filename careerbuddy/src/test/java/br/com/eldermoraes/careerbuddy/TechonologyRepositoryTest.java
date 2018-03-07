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

import br.com.eldermoraes.careerbuddy.cdi.CDIExtension;
import java.util.List;
import java.util.Optional;
import org.jnosql.artemis.Database;
import org.jnosql.artemis.DatabaseType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(CDIExtension.class)
public class TechonologyRepositoryTest {

    @Inject
    @Database(DatabaseType.GRAPH)
    private TechnologyRepository repository;

    @Test
    public void shouldFindById() {
        Name name = Name.of("Graph database");
        Technology technology = new Technology(name.get());

        repository.save(technology);
        assertTrue(repository.findByName(name.get()).isPresent());
    }

    @Test
    public void shouldDeleteById() {
        Name name = Name.of("Graph database");

        Technology technology = new Technology(name.get());

        repository.save(technology);
        assertTrue(repository.findByName(name.get()).isPresent());
        repository.deleteByName(name.get());
        assertFalse(repository.findByName(name.get()).isPresent());
    }
    
    @Test
    public void shouldFindAll() {
        Name name = Name.of("Graph database");
        
        Technology technology = new Technology(name.get());
        
        repository.save(technology);
        
        List<Technology> techs = repository.findAll();
        assertFalse(techs.isEmpty());
    }

}