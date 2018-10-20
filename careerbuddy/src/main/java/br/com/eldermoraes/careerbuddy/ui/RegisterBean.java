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
import br.com.eldermoraes.careerbuddy.BuddyService;
import br.com.eldermoraes.careerbuddy.CityDTO;
import br.com.eldermoraes.careerbuddy.Register;
import br.com.eldermoraes.careerbuddy.TechnologyDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author eldermoraes
 */
@Named
@ViewScoped
public class RegisterBean implements Serializable {

    private String cityName;
    private BuddyDTO buddy;

    private BuddyDTO buddyDTO;
    private String buddyName;
    private Double buddySalary;

    @Inject
    private BuddyService buddyService;

    @Inject
    private CityBean cityBean;
    
    @Inject
    private TechnologyBean technologyBean;

    private String tech1;
    private String level1;
    private String tech2;
    private String level2;

    private final List<String> listLevel = new ArrayList<>();
    private List<CityDTO> listCity = new ArrayList<>();
    private List<TechnologyDTO> listTechnology = new ArrayList<>();

    public RegisterBean() {
        loadLevel();
        loadCity();
        loadTechnology();
    }

    public void register() {
        buddy = BuddyDTO.of(new Buddy(buddyName, buddySalary));
        buddyService.getRegister()
                .buddy(buddy).lives(cityName)
                .works(tech1)
                .with(level1);
        buddyService.getRegister()
                .buddy(buddy)
                .works(tech2)
                .with(level2);
    }

    private void loadLevel() {
        listLevel.add("beginner");
        listLevel.add("intermediate");
        listLevel.add("advanced");
    }

    private void loadCity() {
        listCity.addAll(cityBean.getCities());
    }

    private void loadTechnology() {
        listTechnology.addAll(technologyBean.getTechnologies());
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public BuddyDTO getBuddyDTO() {
        return buddyDTO;
    }

    public void setBuddyDTO(BuddyDTO buddyDTO) {
        this.buddyDTO = buddyDTO;
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

}
