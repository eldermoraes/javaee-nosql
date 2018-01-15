package br.com.eldermoraes.careerbuddy;

import org.jnosql.artemis.Repository;

public interface TechnologyRepository extends Repository<Technology, Long> {

    Technology findByName(String name);
}
