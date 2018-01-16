package br.com.eldermoraes.careerbuddy;

import java.util.function.Supplier;

public enum  Edges implements Supplier<String> {

    WORKS_WITH {
        @Override
        public String get() {
            return name();
        }
    }, LIVES_IN {
        @Override
        public String get() {
            return name();
        }
    };
}
