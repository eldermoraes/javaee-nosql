package br.com.eldermoraes.careerbuddy;

import java.io.Serializable;
import org.jnosql.artemis.Column;
import org.jnosql.artemis.Entity;
import org.jnosql.artemis.Id;

/**
 *
 * @author eldermoraes
 */
@Entity
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    
    @Column
    private String name;

    public City(String name) {
        this.name = name;
    }

    public City() {
    }    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof City)) {
            return false;
        }
        City other = (City) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.eldermoraes.careerbuddy.City[ id=" + id + " ]";
    }

    public static City of(String name){
        return new City(name);
    }
    
}
