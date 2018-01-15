package br.com.eldermoraes.careerbuddy;

import org.jnosql.artemis.Database;
import org.jnosql.artemis.DatabaseType;
import org.jnosql.artemis.graph.Transactional;

import javax.inject.Inject;

public class BuddyLoader {

    @Inject
    @Database(DatabaseType.GRAPH)
    private BuddyRepository buddyRepository;

    @Inject
    @Database(DatabaseType.GRAPH)
    private TechnologyRepository technologyRepository;

    @Inject
    @Database(DatabaseType.GRAPH)
    private CityRepository cityRepository;


    @Transactional
    public void loadEdges() {

        buddyRepository.save(Buddy.of(Enums.Buddy.JOSE, 3_000D));
        buddyRepository.save(Buddy.of(Enums.Buddy.MARIO, 5_000D));
        buddyRepository.save(Buddy.of(Enums.Buddy.JOAO, 9_000D));
        buddyRepository.save(Buddy.of(Enums.Buddy.PEDRO, 14_000D));

        cityRepository.save(City.of(Enums.City.SAO_PAULO));
        cityRepository.save(City.of(Enums.City.BELO_HORIZONTE));
        cityRepository.save(City.of(Enums.City.SALVADOR));
        cityRepository.save(City.of(Enums.City.RIO_JANEIRO));
        cityRepository.save(City.of(Enums.City.CURITIBA));

        technologyRepository.save(Technology.of(Enums.Technology.JAVA));
        technologyRepository.save(Technology.of(Enums.Technology.NOSQL));
        technologyRepository.save(Technology.of(Enums.Technology.CLOUD));
        technologyRepository.save(Technology.of(Enums.Technology.CONTAINER));
        technologyRepository.save(Technology.of(Enums.Technology.GO));
    }
}
