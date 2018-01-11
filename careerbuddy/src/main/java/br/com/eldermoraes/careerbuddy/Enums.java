package br.com.eldermoraes.careerbuddy;

/**
 *
 * @author eldermoraes
 */
public class Enums {

    public enum Technology {
        JAVA,
        NOSQL,
        CLOUD,
        CONTAINER,
        GO
    }

    public enum City {
        SAO_PAULO,
        BELO_HORIZONTE,
        SALVADOR,
        RIO_JANEIRO,
        CURITIBA
    }

    public enum Buddy {
        JOSE,
        MARIO,
        JOAO,
        PEDRO
    }

    public enum Edge {
        WORKS_WITH,
        LIVES_IN
    }

    public enum Level {
        BEGINNER,
        INTERMEDIATE,
        ADVANCED
    }

    public enum Entity {
        CITY,
        TECHNOLOGY,
        BUDDY,
        LEVEL
    }
    
    public enum Property{
        NAME
    }
}
