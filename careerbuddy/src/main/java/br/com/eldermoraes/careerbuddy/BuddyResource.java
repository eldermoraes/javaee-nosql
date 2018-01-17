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
import org.jnosql.artemis.Database;
import org.jnosql.artemis.graph.Transactional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.jnosql.artemis.DatabaseType.GRAPH;

@ApplicationScoped
@Path("buddies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class BuddyResource {


    @Inject
    @Database(GRAPH)
    private BuddyRepository buddyRepository;

    @Inject
    @Database(GRAPH)
    private CityRepository cityRepository;

    @Inject
    @Database(GRAPH)
    private TechnologyRepository technologyRepository;

    @Inject
    private BuddyService service;

    @POST
    public void insert(@Valid BuddyDTO buddy) {

        buddyRepository.findByName(buddy.getName()).ifPresent(b -> {
            throw new WebApplicationException("There is a buddy that already does exist", Response.Status.BAD_REQUEST);
        });

        buddyRepository.save(buddy.toEnity());
    }


    @PUT
    @Path("{buddy}")
    public void insert(@PathParam("buddy") @Name String buddyName, @Valid BuddyDTO dto) {
        Buddy buddy = buddyRepository.findByName(buddyName)
                .orElseThrow(() -> new WebApplicationException("buddy does not found", Response.Status.NOT_FOUND));

        buddy.setSalary(dto.getSalary());
        buddyRepository.save(buddy);
    }

    @DELETE
    @Path("{buddy}")
    public void delete(@PathParam("buddy") @Name String buddyName) {
        buddyRepository.deleteByName(buddyName);
    }


    @POST
    @Path("{buddy}/live/{city}")
    public void lives(@PathParam("buddy") @Name String buddyName, @PathParam("city") @Name String cityName) {

        Buddy buddy = buddyRepository.findByName(buddyName)
                .orElseThrow(() -> new WebApplicationException("buddy does not found", Response.Status.NOT_FOUND));

        City city = cityRepository.findByName(cityName)
                .orElseThrow(() -> new WebApplicationException("city does not found", Response.Status.NOT_FOUND));


        service.live(buddy, city);
    }

    @POST
    @Path("{buddy}/works/{technology}")
    public void works(@PathParam("buddy") @Name String buddyName, @PathParam("technology") @Name String technologyName) {

        Buddy buddy = buddyRepository.findByName(buddyName)
                .orElseThrow(() -> new WebApplicationException("buddy does not found", Response.Status.NOT_FOUND));

        Technology technology = technologyRepository.findByName(technologyName)
                .orElseThrow(() -> new WebApplicationException("city does not found", Response.Status.NOT_FOUND));

        service.work(buddy, technology);
    }

    @POST
    @Path("{buddy}/works/{technology}/level/{level}")
    public void worksLevel(@PathParam("buddy") @Name String buddyName,
                           @PathParam("technology") @Name String technologyName,
                           @PathParam("level") TechnologyLevel level) {

        Buddy buddy = buddyRepository.findByName(buddyName)
                .orElseThrow(() -> new WebApplicationException("buddy does not found", Response.Status.NOT_FOUND));

        Technology technology = technologyRepository.findByName(technologyName)
                .orElseThrow(() -> new WebApplicationException("city does not found", Response.Status.NOT_FOUND));

        service.work(buddy, technology, level);
    }
}
