package br.com.eldermoraes.careerbuddy;

import org.jnosql.artemis.Repository;

public interface BuddyRepository extends Repository<Buddy, Long> {


    Buddy findByName(String name);

}
