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

import static br.com.eldermoraes.careerbuddy.Enums.City.SAO_PAULO;
import static br.com.eldermoraes.careerbuddy.Enums.Technology.CONTAINER;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(CDIExtension.class)
class BuddyServiceTest {

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

        List<Buddy> javaDevelopers = buddyRepository.findByTechnology(Enums.Technology.JAVA.name());

        assertFalse(javaDevelopers.isEmpty());
        assertEquals(2, javaDevelopers.size());
        assertAll(() -> {
            assertEquals(Enums.Buddy.JOAO.name(), javaDevelopers.get(0).getName());
        }, () -> {
            assertEquals(Enums.Buddy.JOSE.name(), javaDevelopers.get(1).getName());
        });
    }

    @Test
    public void shouldFindByCity() {
        List<Buddy> paulistano = buddyRepository.findByCity(SAO_PAULO.name());
        assertFalse(paulistano.isEmpty());
        assertEquals(2, paulistano.size());

        assertAll(() -> {
            assertEquals(Enums.Buddy.JOSE.name(), paulistano.get(0).getName());
        }, () -> {
            assertEquals(Enums.Buddy.PEDRO.name(), paulistano.get(1).getName());
        });
    }


    @Test
    public void shouldFindByCityAndTechnology() {
        List<Buddy> paulistanoWithGo = buddyRepository
                .findByTechnologyAndCity(CONTAINER.name(), SAO_PAULO.name());

        assertFalse(paulistanoWithGo.isEmpty());
        assertEquals(2, paulistanoWithGo.size());

        assertAll(() -> {
            assertEquals(Enums.Buddy.JOSE.name(), paulistanoWithGo.get(0).getName());
        }, () -> {
            assertEquals(Enums.Buddy.PEDRO.name(), paulistanoWithGo.get(1).getName());
        });
    }
}