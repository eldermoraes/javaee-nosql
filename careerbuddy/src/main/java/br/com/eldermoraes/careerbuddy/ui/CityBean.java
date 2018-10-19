package br.com.eldermoraes.careerbuddy.ui;

import br.com.eldermoraes.careerbuddy.CityDTO;
import br.com.eldermoraes.careerbuddy.CityRepository;
import org.jnosql.artemis.Database;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.jnosql.artemis.DatabaseType.GRAPH;

/**
 * @author eldermoraes
 */
@Named
@ViewScoped
public class CityBean implements Serializable {


    @Inject
    @Database(GRAPH)
    private CityRepository cityRepository;

    private List<CityDTO> cities = Collections.emptyList();

    @PostConstruct
    public void setUp() {
        this.cities = cityRepository.findAll().map(CityDTO::new).collect(Collectors.toList());
    }

    public List<CityDTO> getCities() {
        return cities;
    }
}
