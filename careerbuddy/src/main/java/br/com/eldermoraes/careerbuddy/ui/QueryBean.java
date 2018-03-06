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
package br.com.eldermoraes.careerbuddy.ui;

import br.com.eldermoraes.careerbuddy.TechnologyDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author eldermoraes
 */
@ManagedBean(name="userBean")
@SessionScoped
public class QueryBean implements Serializable{
    
    private Client client;
    private WebTarget targetTechnologies;
    private final List<TechnologyDTO> listTechnology = new ArrayList<>();
    private String technologyName;
    
    @PostConstruct
    public void init() {
        client = ClientBuilder.newBuilder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
        targetTechnologies = client.target("http://localhost:8080/careerbudy/technologies");
        loadTechnology();
    }
    
    private void loadTechnology(){
        Response response = targetTechnologies
                .path("findAll")
                .request(MediaType.APPLICATION_JSON)
                .get();
        listTechnology.addAll(response.readEntity(List.class));
    }

    public String getTechnologyName() {
        return technologyName;
    }

    public void setTechnologyName(String technologyName) {
        this.technologyName = technologyName;
    }

    public List<TechnologyDTO> getListTechnology() {
        return listTechnology;
    }
}
