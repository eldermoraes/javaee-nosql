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
        GOLANG;
        public String getName() {
            return Name.of(this.name()).get();
        }
    }

    public enum City {
        SAO_PAULO,
        BELO_HORIZONTE,
        SALVADOR,
        RIO_JANEIRO,
        CURITIBA;
        public String getName() {
            return Name.of(this.name()).get();
        }
    }

    public enum Buddy {
        JOSE,
        MARIO,
        JOAO,
        PEDRO;

        public String getName() {
            return Name.of(this.name()).get();
        }
    }
}
