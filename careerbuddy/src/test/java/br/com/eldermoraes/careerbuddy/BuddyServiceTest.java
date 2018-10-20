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
import org.jnosql.artemis.Database;
import org.jnosql.artemis.DatabaseType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.inject.Inject;
import java.util.List;
import java.util.function.Predicate;

import static br.com.eldermoraes.careerbuddy.Enums.Buddy.MARIO;
import static br.com.eldermoraes.careerbuddy.Enums.City.SAO_PAULO;
import static br.com.eldermoraes.careerbuddy.Enums.Technology.CONTAINER;
import static br.com.eldermoraes.careerbuddy.Enums.Technology.JAVA;
import static br.com.eldermoraes.careerbuddy.TechnologyLevel.ADVANCED;
import static br.com.eldermoraes.careerbuddy.TechnologyLevel.INTERMEDIATE;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(CDIExtension.class)
class BuddyServiceTest {
    private static final Predicate<String> IS_JOSE = Enums.Buddy.JOSE.name()::equals;
    private static final Predicate<String> IS_PEDRO = Enums.Buddy.PEDRO.name()::equals;

    @Inject
    private BuddyService service;

    @Inject
    private BuddyLoader loader;

    @Inject
    @Database(DatabaseType.GRAPH)
    private BuddyRepository buddyRepository;

    @Inject
    @Database(DatabaseType.GRAPH)
    private CityRepository cityRepository;

    @Inject
    @Database(DatabaseType.GRAPH)
    private TechnologyRepository technologyRepository;

    @BeforeEach
    public void setUp() {
        loader.loadVertex();
        loader.loadEdges();
    }

    @AfterEach
    public void after() {
        loader.clean();
    }


    //@Test
    public void shouldFindByTechnology() {

        List<Buddy> javaDevelopers = service.findByTechnology(JAVA.getName());

        assertFalse(javaDevelopers.isEmpty());

        assertAll(() -> {
            assertEquals(2, javaDevelopers.size());
        }, () -> {

        });
    }

    //@Test
    public void shouldFindByTechnologyWithLevel() {

        List<Buddy> javaDevelopers = service.findByTechnology(JAVA.getName(), ADVANCED);

        assertFalse(javaDevelopers.isEmpty());
        assertEquals(1, javaDevelopers.size());
        assertAll(() -> {
            assertTrue(javaDevelopers.stream().map(Buddy::getDisplayName).allMatch(IS_JOSE));
        });
    }

    //@Test
    public void shouldFindByCity() {
        List<Buddy> paulistano = service.findByCity(SAO_PAULO.getName());
        assertFalse(paulistano.isEmpty());


        assertAll(() -> {
            assertEquals(2, paulistano.size());
        }, () -> {
            assertTrue(paulistano.stream().map(Buddy::getDisplayName).allMatch(IS_PEDRO.or(IS_JOSE)));
        });
    }


    //@Test
    public void shouldFindByCityAndTechnology() {
        List<Buddy> paulistanoWithContainer = service
                .findByTechnologyAndCity(CONTAINER.getName(), SAO_PAULO.getName());

        assertFalse(paulistanoWithContainer.isEmpty());


        assertAll(() -> {
            assertEquals(2, paulistanoWithContainer.size());
        }, () -> {
            assertTrue(paulistanoWithContainer.stream().map(Buddy::getDisplayName).allMatch(IS_PEDRO.or(IS_JOSE)));
        });
    }


    @Test
    public void shouldLive() {
        String buddyName = MARIO.getName();
        City saoPaulo = cityRepository.findByName(Enums.City.SAO_PAULO.getName()).orElseThrow(() -> new RuntimeException());
        Buddy mario = buddyRepository.findByName(buddyName).orElseThrow(() -> new RuntimeException());

        service.live(mario, saoPaulo);
        assertTrue(service.findByCity(Enums.City.SAO_PAULO.getName()).stream().anyMatch(b -> b.getDisplayName().equals(MARIO.name())));

    }

    @Test
    public void shouldWork() {
        String buddyName = MARIO.getName();

        Technology java = technologyRepository.findByName(JAVA.getName()).orElseThrow(() -> new RuntimeException());
        Buddy mario = buddyRepository.findByName(buddyName).orElseThrow(() -> new RuntimeException());

        service.work(mario, java);
        assertTrue(service.findByTechnology(JAVA.getName()).stream().anyMatch(b -> b.getDisplayName().equals(MARIO.name())));
    }

    @Test
    public void shouldWorkLevel() {
        String buddyName = MARIO.getName();

        Technology java = technologyRepository.findByName(JAVA.getName()).orElseThrow(() -> new RuntimeException());
        Buddy mario = buddyRepository.findByName(buddyName).orElseThrow(() -> new RuntimeException());

        service.work(mario, java, INTERMEDIATE);
        assertTrue(service.findByTechnology(JAVA.getName(), INTERMEDIATE).stream().anyMatch(b -> b.getDisplayName().equals(MARIO.name())));
    }
}