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

import org.jnosql.diana.api.Settings;

import java.io.Serializable;
import java.util.Objects;

final class Neo4JConfiguration implements Serializable {

    private final String url;

    private final String user;

    private final String password;

    public Neo4JConfiguration(Settings settings) {
        this.url = settings.getOrDefault("url", "bolt://localhost:7687").toString();
        this.user = settings.getOrDefault("admin", "neo4j").toString();
        this.password = settings.getOrDefault("password", "admin").toString();
    }


    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Neo4JConfiguration)) {
            return false;
        }
        Neo4JConfiguration that = (Neo4JConfiguration) o;
        return Objects.equals(url, that.url) &&
                Objects.equals(user, that.user) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, user, password);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Neo4JConfiguration{");
        sb.append("url='").append(url).append('\'');
        sb.append(", user='").append(user).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
