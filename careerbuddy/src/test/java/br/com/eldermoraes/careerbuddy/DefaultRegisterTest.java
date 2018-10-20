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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.inject.Inject;

import java.util.Locale;

import static br.com.eldermoraes.careerbuddy.Enums.City.SAO_PAULO;
import static br.com.eldermoraes.careerbuddy.Enums.Technology.JAVA;
import static br.com.eldermoraes.careerbuddy.TechnologyLevel.ADVANCED;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(CDIExtension.class)
class DefaultRegisterTest {

    @Inject
    private BuddyService service;

    @Inject
    private BuddyLoader loader;


    @BeforeEach
    public void setUp() {
        loader.loadVertex();
    }


    @Test
    public void shouldCreateRelationship() {

        Buddy buddy = new Buddy("Elder", 50_000D);

        Register register = service.getRegister();
        register.buddy(BuddyDTO.of(buddy))
                .lives(SAO_PAULO.name())
                .works(JAVA.name()).with(ADVANCED.get());

        Buddy result = service.findByCity(SAO_PAULO.name().toLowerCase(Locale.US)).get(0);
        assertEquals(buddy.getDisplayName(), result.getDisplayName());

        result = service.findByTechnology(JAVA.name().toLowerCase(Locale.US)).get(0);
        assertEquals(buddy.getDisplayName(), result.getDisplayName());
    }
}