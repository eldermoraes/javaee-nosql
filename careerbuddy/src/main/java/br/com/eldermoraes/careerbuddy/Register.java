package br.com.eldermoraes.careerbuddy;

public interface Register {

    RegisterCity buddy(BuddyDTO dto);

    interface RegisterCity {
        RegisterWork lives(String city);

        RegisterLevel works(String technology);
    }

    interface RegisterWork {
        RegisterLevel works(String technology);
    }

    interface RegisterLevel {
        void with(String level);
    }

}
