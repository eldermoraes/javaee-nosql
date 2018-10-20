package br.com.eldermoraes.careerbuddy.ui;

import br.com.eldermoraes.careerbuddy.TechnologyLevel;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author eldermoraes
 */
@Named
@ApplicationScoped
public class LevelBean {

    private List<String> levels = Collections.emptyList();


    @PostConstruct
    public void setUp() {
        levels = Stream.of(TechnologyLevel.values())
                .map(t -> t.name().toLowerCase(Locale.US))
                .collect(Collectors.toList());
    }

    public List<String> getLevels() {
        return levels;
    }
}
