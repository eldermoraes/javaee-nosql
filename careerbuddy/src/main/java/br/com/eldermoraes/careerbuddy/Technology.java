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
public class Technology implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    
    @Column
    private String name;

    public Technology(String name) {
        this.name = name;
    }

    public Technology() {
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        if (!(object instanceof Technology)) {
            return false;
        }
        Technology other = (Technology) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.eldermoraes.careerbuddy.Technology[ id=" + id + " ]";
    }

    public static Technology of (String name){
        return new Technology(name);
    }
}
