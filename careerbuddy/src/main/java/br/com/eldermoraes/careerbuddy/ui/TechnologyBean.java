package br.com.eldermoraes.careerbuddy.ui;

import br.com.eldermoraes.careerbuddy.TechnologyDTO;
import br.com.eldermoraes.careerbuddy.TechnologyRepository;
import org.jnosql.artemis.Database;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.jnosql.artemis.DatabaseType.GRAPH;

/**
 * @author eldermoraes
 */
@Named
@ViewScoped
public class TechnologyBean {

    private List<TechnologyDTO> technologies = Collections.emptyList();

    @Inject
    @Database(GRAPH)
    private TechnologyRepository technologyRepository;


    @PostConstruct
    public void setUp(){
        this.technologies = technologyRepository.findAll()
                .stream().map(TechnologyDTO::new).collect(Collectors.toList());
    }

    public List<TechnologyDTO> getTechnologies() {
        return technologies;
    }
}
