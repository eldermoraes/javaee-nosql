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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.inject.Inject;
import java.util.List;
import java.util.function.Predicate;

import static br.com.eldermoraes.careerbuddy.Enums.City.SAO_PAULO;
import static br.com.eldermoraes.careerbuddy.Enums.Technology.CONTAINER;
import static br.com.eldermoraes.careerbuddy.Enums.Technology.JAVA;
import static br.com.eldermoraes.careerbuddy.TechnologyLevel.ADVANCED;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(CDIExtension.class)
class BuddyServiceTest {
    private static final Predicate<String> IS_JOSE = Enums.Buddy.JOSE.name()::equals;
    private static final Predicate<String> IS_PEDRO = Enums.Buddy.PEDRO.name()::equals;

    @Inject
    private BuddyService buddyRepository;

    @Inject
    private BuddyLoader loader;

    @BeforeEach
    public void setUp() {
        loader.loadVertex();
        loader.loadEdges();
    }

    @AfterEach
    public void after() {
        loader.clean();
    }


    @Test
    public void shouldFindByTechnology() {

        List<Buddy> javaDevelopers = buddyRepository.findByTechnology(JAVA.name());

        assertFalse(javaDevelopers.isEmpty());

        assertAll(() -> {
            assertEquals(2, javaDevelopers.size());
        }, () -> {

        });
    }

    @Test
    public void shouldFindByTechnologyWithLevel() {

        List<Buddy> javaDevelopers = buddyRepository.findByTechnology(JAVA.name(), ADVANCED);

        assertFalse(javaDevelopers.isEmpty());
        assertEquals(1, javaDevelopers.size());
        assertAll(() -> {
            assertTrue(javaDevelopers.stream().map(Buddy::getName).allMatch(IS_JOSE));
        });
    }

    @Test
    public void shouldFindByCity() {
        List<Buddy> paulistano = buddyRepository.findByCity(SAO_PAULO.name());
        assertFalse(paulistano.isEmpty());


        assertAll(() -> {
            assertEquals(2, paulistano.size());
        }, () -> {
            assertTrue(paulistano.stream().map(Buddy::getName).allMatch(IS_PEDRO.or(IS_JOSE)));
        });
    }


    @Test
    public void shouldFindByCityAndTechnology() {
        List<Buddy> paulistanoWithContainer = buddyRepository
                .findByTechnologyAndCity(CONTAINER.name(), SAO_PAULO.name());

        assertFalse(paulistanoWithContainer.isEmpty());


        assertAll(() -> {
            assertEquals(2, paulistanoWithContainer.size());
        }, () -> {
            assertTrue(paulistanoWithContainer.stream().map(Buddy::getName).allMatch(IS_PEDRO.or(IS_JOSE)));
        });
    }
}