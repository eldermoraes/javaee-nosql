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

import javax.validation.constraints.PositiveOrZero;

public class BuddyDTO {

    @Name
    private String name;

    @PositiveOrZero
    private Double salary;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Buddy toEnity() {
        return new Buddy(name, salary);
    }


    public static BuddyDTO of(Buddy buddy) {
        BuddyDTO dto = new BuddyDTO();
        dto.setName(buddy.getName());
        dto.setSalary(buddy.getSalary());
        return dto;
    }
}
