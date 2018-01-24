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

package br.com.eldermoraes.careerbuddy.driver;

import br.com.eldermoraes.careerbuddy.Name;
import org.jnosql.artemis.AttributeConverter;

public class NameConverter implements AttributeConverter<Name, String> {

    @Override
    public String convertToDatabaseColumn(Name attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.get();
    }

    @Override
    public Name convertToEntityAttribute(String dbData) {

        if (dbData == null) {
            return null;
        }
        return Name.of(dbData);
    }
}
