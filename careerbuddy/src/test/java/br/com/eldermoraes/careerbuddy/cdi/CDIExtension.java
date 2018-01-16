package br.com.eldermoraes.careerbuddy.cdi;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import javax.inject.Inject;
import java.lang.reflect.Field;

public class CDIExtension implements TestInstancePostProcessor {

    private static final SeContainer CONTAINER;

    static {
        CONTAINER = SeContainerInitializer.newInstance().initialize();
        Runtime.getRuntime().addShutdownHook(new Thread(CONTAINER::close));
    }
    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context) throws IllegalAccessException {

        for (Field field : testInstance.getClass().getDeclaredFields()) {
            if(field.getAnnotation(Inject.class) != null) {
                Object injected = CONTAINER.select(field.getType()).get();
                field.setAccessible(true);
                field.set(testInstance, injected);
            }

        }


    }



}