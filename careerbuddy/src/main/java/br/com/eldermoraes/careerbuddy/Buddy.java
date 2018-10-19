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

import java.io.Serializable;
import java.util.Objects;

import br.com.eldermoraes.careerbuddy.driver.NameConverter;
import org.jnosql.artemis.Column;
import org.jnosql.artemis.Convert;
import org.jnosql.artemis.Entity;
import org.jnosql.artemis.Id;

/**
 *
 * @author eldermoraes
 */
@Entity(value = "BUDDY")
public class Buddy implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private Long id;
    
    @Column
    @Convert(NameConverter.class)
    private Name name;

    @Column
    private String displayName;

    @Column
    private Double salary;

    
    Buddy(){
        
    }

    public Buddy(String name, Double salary) {
        this.name = Name.of(name);
        this.displayName = name;
        this.salary = salary;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Buddy)) {
            return false;
        }
        Buddy buddy = (Buddy) o;
        return Objects.equals(id, buddy.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Buddy{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", salary=").append(salary);
        sb.append('}');
        return sb.toString();
    }

    public boolean isNew() {
        return id == null;
    }
}
