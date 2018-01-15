package br.com.eldermoraes.careerbuddy;

import org.jnosql.artemis.Repository;

import java.lang.annotation.Repeatable;

public interface BuddyRepository extends Repository<Buddy, Long> {
}
