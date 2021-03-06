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

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NameTest {


    @Test
    public void shouldReturnErrorWhenTheValueIsNull() {
        assertThrows(NullPointerException.class, () -> {
            Name.of(null);
        });
    }

    @Test
    public void shouldReturnValueInLowerCase() {
        String value = "Ada";
        Name name = Name.of(value);
        assertEquals(value.toLowerCase(Locale.US), name.get());
    }

    @Test
    public void shouldKeepValueWhenNameIsAlreadyInLowerCase() {
        String value = "ada";
        Name name = Name.of(value);
        assertEquals(value, name.get());
    }

    @Test
    public void shouldRepleaceSpace() {
        String value = "Ada Lovelace";
        Name name = Name.of(value);
        assertEquals("ada_lovelace", name.get());
    }

}