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

import br.com.eldermoraes.careerbuddy.validation.Name;
import java.util.ArrayList;
import java.util.List;
import org.jnosql.artemis.Database;
import org.jnosql.artemis.graph.Transactional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.jnosql.artemis.DatabaseType.GRAPH;

@ApplicationScoped
@Path("technologies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class TechnologyResource {


    @Inject
    @Database(GRAPH)
    private TechnologyRepository cityRepository;


    @POST
    public void insert(@Name String name) {

        cityRepository.findByName(name).ifPresent(b -> {
            throw new WebApplicationException("There is a technology that already does exist", Response.Status.BAD_REQUEST);
        });

        cityRepository.save(new Technology(name));
    }

    @GET
    @Path("{name}")
    public TechnologyDTO get(@PathParam("name")String name) {
        Technology technology = cityRepository.findByName(name)
                .orElseThrow(() -> new WebApplicationException("technology does not found", Response.Status.NOT_FOUND));

        return new TechnologyDTO(technology);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TechnologyDTO> findAll() {
        List<Technology> technology = cityRepository.findAll();
        
        if (technology.isEmpty()) throw new WebApplicationException("technology list is empty", Response.Status.NOT_FOUND);

        List<TechnologyDTO> dto = new ArrayList<>();
        technology.forEach((t) -> {
            dto.add(new TechnologyDTO(t));
        });
        
        return dto;
    }    


    @DELETE
    @Path("{name}")
    public void delete(@PathParam("name") @Name String buddyName) {
        cityRepository.deleteByName(buddyName);
    }
}
