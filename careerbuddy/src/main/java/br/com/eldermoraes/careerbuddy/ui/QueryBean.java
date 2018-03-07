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
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author eldermoraes
 */
@Named
@ViewScoped
public class QueryBean implements Serializable{
    
    private final Client client;
    private final WebTarget targetTechnologies;
    private final WebTarget targetCities;
    private final WebTarget targetBuddies;
    private final List<TechnologyDTO> listTechnology = new ArrayList<>();
    private final List<CityDTO> listCity = new ArrayList<>();
    private final List<String> listLevel = new ArrayList<>();
    private String technologyName1;
    private String technologyName2;
    private String technologyName3;
    private String level;
    private String cityName1;
    private String cityName2;
    private String searchResult;
    
    public QueryBean() {
        client = ClientBuilder.newBuilder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
        targetTechnologies = client.target("http://localhost:8080/careerbuddy/resource/technologies");
        targetCities = client.target("http://localhost:8080/careerbuddy/resource/cities");
        targetBuddies = client.target("http://localhost:8080/careerbuddy/resource/buddies");
        loadTechnology();
        loadLevel();
        loadCity();
    }
    
    private void loadTechnology(){
        Response response = targetTechnologies
                .path("findAll")
                .request(MediaType.APPLICATION_JSON)
                .get();
        listTechnology.addAll(response.readEntity(List.class));
    }
    
    private void loadCity(){
        Response response = targetCities
                .path("findAll")
                .request(MediaType.APPLICATION_JSON)
                .get();
        listCity.addAll(response.readEntity(List.class));
    }
    
    private void loadLevel(){
        listLevel.add("beginner");
        listLevel.add("intermediate");
        listLevel.add("advanced");
    }
    
    public void searchBuddiesByTechnology(){
        Response response = targetBuddies
                .path("technologies")
                .path(technologyName1.toLowerCase())
                .request(MediaType.APPLICATION_JSON)
                .get();
        searchResult = response.readEntity(String.class);
    }
    
    public void searchBuddiesByTechnologyAndLevel(){
        Response response = targetBuddies
                .path("technologies")
                .path(technologyName2.toLowerCase())
                .path(level)
                .request(MediaType.APPLICATION_JSON)
                .get();
        searchResult = response.readEntity(String.class);
    }    
    
    public void searchBuddiesByCity(){
        Response response = targetBuddies
                .path("cities")
                .path(cityName1.toLowerCase())
                .request(MediaType.APPLICATION_JSON)
                .get();
        searchResult = response.readEntity(String.class);
    }

    public void searchBuddiesByCityAndTechnology(){
        Response response = targetBuddies
                .path("cities")
                .path(cityName2.toLowerCase())
                .path("technologies")
                .path(technologyName3.toLowerCase())
                .request(MediaType.APPLICATION_JSON)
                .get();
        searchResult = response.readEntity(String.class);
    }    

    public String getTechnologyName1() {
        return technologyName1;
    }

    public void setTechnologyName1(String technologyName1) {
        this.technologyName1 = technologyName1;
    }

    public List<TechnologyDTO> getListTechnology() {
        return listTechnology;
    }

    public String getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(String searchResult) {
        this.searchResult = searchResult;
    }

    public List<String> getListLevel() {
        return listLevel;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCityName1() {
        return cityName1;
    }

    public void setCityName1(String cityName1) {
        this.cityName1 = cityName1;
    }

    public List<CityDTO> getListCity() {
        return listCity;
    }

    public String getTechnologyName2() {
        return technologyName2;
    }

    public void setTechnologyName2(String technologyName2) {
        this.technologyName2 = technologyName2;
    }

    public String getTechnologyName3() {
        return technologyName3;
    }

    public void setTechnologyName3(String technologyName3) {
        this.technologyName3 = technologyName3;
    }

    public String getCityName2() {
        return cityName2;
    }

    public void setCityName2(String cityName2) {
        this.cityName2 = cityName2;
    }
}
