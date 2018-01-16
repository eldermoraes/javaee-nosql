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
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(CDIExtension.class)
public class BuddyLoaderTest {


    @Inject
    private BuddyLoader buddyLoader;

    @Inject
    private Graph graph;


    @BeforeEach
    public void SetUp() {
        buddyLoader.clean();
    }

    @Test
    public void shouldCreateEdges() {
        buddyLoader.loadVertex();
        assertEquals(14, graph.traversal().V().toList().size());
    }

    @Test
    public void shouldCreateEntitiesOnce() {
        buddyLoader.loadVertex();
        assertEquals(14, graph.traversal().V().toList().size());
        buddyLoader.loadVertex();
        assertEquals(14, graph.traversal().V().toList().size());
    }

    @Test
    public void shouldReturnErrorWhenLoadEdgesWithVertexEmpty() {
        Assertions.assertThrows(IllegalStateException.class, buddyLoader::loadEdges);
    }

    @Test
    public void shouldCreateEdge() {
        buddyLoader.loadVertex();
        buddyLoader.loadEdges();
    }
}