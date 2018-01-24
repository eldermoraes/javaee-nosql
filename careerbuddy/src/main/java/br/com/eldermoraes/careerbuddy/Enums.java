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

/**
 *
 * @author eldermoraes
 */
public class Enums {

    public enum Technology {
        JAVA,
        NOSQL,
        CLOUD,
        CONTAINER,
        GO;
        public String getName() {
            return Name.of(this.name()).get();
        }
    }

    public enum City {
        SAO_PAULO,
        BELO_HORIZONTE,
        SALVADOR,
        RIO_JANEIRO,
        CURITIBA;
        public String getName() {
            return Name.of(this.name()).get();
        }
    }

    public enum Buddy {
        JOSE,
        MARIO,
        JOAO,
        PEDRO;

        public String getName() {
            return Name.of(this.name()).get();
        }
    }
}
