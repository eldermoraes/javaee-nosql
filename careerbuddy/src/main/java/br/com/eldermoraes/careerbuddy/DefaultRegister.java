package br.com.eldermoraes.careerbuddy;

import org.jnosql.artemis.EntityNotFoundException;
import org.jnosql.artemis.graph.GraphTemplate;

import java.util.Optional;

final class DefaultRegister implements Register, Register.RegisterCity, Register.RegisterWork, Register.RegisterLevel {

    private final GraphTemplate graphTemplate;

    private Buddy buddy;

    private Technology technology;

    DefaultRegister(GraphTemplate graphTemplate) {
        this.graphTemplate = graphTemplate;
    }

    @Override
    public RegisterCity buddy(BuddyDTO dto) {
        buddy = findByName(dto).orElse(dto.toEntity());
        buddy.setSalary(dto.getSalary());
        if (buddy.isNew()) {
            graphTemplate.insert(buddy);
        } else {
            graphTemplate.update(buddy);
        }
        graphTemplate.getTransaction().commit();

        buddy = findByName(dto)
                .orElseThrow(() -> new IllegalStateException(""));
        return this;
    }


    @Override
    public RegisterWork lives(String cityName) {
        City city = graphTemplate.getTraversalVertex().hasLabel(City.class)
                .has("name", cityName).<City>getSingleResult()
                .orElseThrow(() -> new EntityNotFoundException("City does not found with the name: " + cityName));

        graphTemplate.edge(buddy, Edges.LIVES, city);
        return this;
    }

    @Override
    public RegisterLevel works(String technologyName) {

        this.technology = graphTemplate.getTraversalVertex().hasLabel(Technology.class)
                .has("name", technologyName).<Technology>getSingleResult()
                .orElseThrow(
                        () -> new EntityNotFoundException("Technology does not found with the name: " + technologyName));

        return this;
    }

    @Override
    public void with(String level) {
        graphTemplate.edge(buddy, Edges.WORKS, technology).add("level", level);
    }


    private Optional<Buddy> findByName(BuddyDTO buddy) {
        return graphTemplate.getTraversalVertex().hasLabel(Buddy.class)
                .has("name", buddy.getName())
                .getSingleResult();
    }

}
