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

import org.apache.tinkerpop.gremlin.structure.Graph;
import org.jnosql.artemis.graph.GraphTemplate;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

/**
 *
 * @author eldermoraes
 */
@ApplicationScoped
public class BuddyBean {

    @Inject
    private GraphTemplate graph;

    @Inject
    private Graph thinkerpop;

    @Inject
    private BuddyLoader buddyLoader;

    @Inject
    private BuddyService service;

    private final Jsonb jsonbBuilder = JsonbBuilder.create();
    

    public String getBuddiesByTechnology(Enums.Technology tech) {
        return jsonbBuilder.toJson(service.findByTechnology(tech.name()));
    }

    public void loadData() {

    }


}
