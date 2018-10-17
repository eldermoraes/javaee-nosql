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

import br.com.eldermoraes.careerbuddy.Buddy;
import br.com.eldermoraes.careerbuddy.BuddyDTO;
import br.com.eldermoraes.careerbuddy.CityDTO;
import br.com.eldermoraes.careerbuddy.TechnologyDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author eldermoraes
 */
@Named
@ViewScoped
public class RegisterBean implements Serializable {

    private final Client client;
    private final WebTarget targetCities;
    private final WebTarget targetTechnologies;
    private final WebTarget targetBuddies;
    
    private final List<CityDTO> listCity = new ArrayList<>();
    private final List<TechnologyDTO> listTechnology = new ArrayList<>();
    private final List<String> listLevel = new ArrayList<>();
    
    private String cityName;
    private BuddyDTO buddy;
    private String buddyName;
    private Double buddySalary;
    
    private String tech1;
    private String level1;
    private String tech2;
    private String level2;
    

    public RegisterBean() {
        client = ClientBuilder.newBuilder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
        targetCities = client.target("http://localhost:8080/careerbuddy/resource/cities");
        targetTechnologies = client.target("http://localhost:8080/careerbuddy/resource/technologies");
        targetBuddies = client.target("http://localhost:8080/careerbuddy/resource/buddies");
        loadCity();
        loadTechnology();
        loadLevel();
    }
    
    private void loadCity(){
        Response response = targetCities
                .path("findAll")
                .request(MediaType.APPLICATION_JSON)
                .get();
        listCity.addAll(response.readEntity(List.class));
    }    
    
    private void loadTechnology(){
        Response response = targetTechnologies
                .path("findAll")
                .request(MediaType.APPLICATION_JSON)
                .get();
        listTechnology.addAll(response.readEntity(List.class));
    }  
    
    private void loadLevel(){
        listLevel.add("beginner");
        listLevel.add("intermediate");
        listLevel.add("advanced");
    }    
    
    public void register(){
        buddy = BuddyDTO.of(new Buddy(buddyName, buddySalary));
        targetBuddies.request(MediaType.APPLICATION_JSON).post(Entity.json(buddy));
                
        targetBuddies
                .path(buddyName)
                .path("lives")
                .path(cityName)
                .request(MediaType.APPLICATION_JSON).get();
                
        targetBuddies
                .path(buddyName)
                .path("works")
                .path(tech1)
                .path(level1)
                .request(MediaType.APPLICATION_JSON).get();

        targetBuddies
                .path(buddyName)
                .path("works")
                .path(tech2)
                .path(level2)
                .request(MediaType.APPLICATION_JSON).get();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public BuddyDTO getBuddy() {
        return buddy;
    }

    public void setBuddy(BuddyDTO buddy) {
        this.buddy = buddy;
    }

    public String getBuddyName() {
        return buddyName;
    }

    public void setBuddyName(String buddyName) {
        this.buddyName = buddyName;
    }

    public Double getBuddySalary() {
        return buddySalary;
    }

    public void setBuddySalary(Double buddySalary) {
        this.buddySalary = buddySalary;
    }

    public String getTech1() {
        return tech1;
    }

    public void setTech1(String tech1) {
        this.tech1 = tech1;
    }

    public String getLevel1() {
        return level1;
    }

    public void setLevel1(String level1) {
        this.level1 = level1;
    }

    public String getTech2() {
        return tech2;
    }

    public void setTech2(String tech2) {
        this.tech2 = tech2;
    }

    public String getLevel2() {
        return level2;
    }

    public void setLevel2(String level2) {
        this.level2 = level2;
    }

    public List<CityDTO> getListCity() {
        return listCity;
    }

    public List<TechnologyDTO> getListTechnology() {
        return listTechnology;
    }

    public List<String> getListLevel() {
        return listLevel;
    }

}
