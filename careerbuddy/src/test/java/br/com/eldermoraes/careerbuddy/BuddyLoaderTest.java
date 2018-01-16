package br.com.eldermoraes.careerbuddy;

import br.com.eldermoraes.careerbuddy.cdi.CDIExtension;
import br.com.eldermoraes.careerbuddy.driver.Team;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.inject.Inject;

@ExtendWith(CDIExtension.class)
public class BuddyLoaderTest {


    @Inject
    private Team team;



    @Test
    public void shouldCreateEdges() {

    }
}