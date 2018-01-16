/*
 * Copyright 2018 Elder Moreas and others
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.eldermoraes.careerbuddy.driver;


import org.jnosql.artemis.ConfigurationReader;
import org.jnosql.artemis.ConfigurationSettingsUnit;
import org.jnosql.artemis.ConfigurationUnit;
import org.jnosql.artemis.configuration.ConfigurationException;
import org.jnosql.diana.api.Settings;
import org.neo4j.driver.v1.AuthToken;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.Annotated;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.neo4j.driver.v1.GraphDatabase.driver;

@ApplicationScoped
class DriverProducer {

    private static final Logger LOGGER = Logger.getLogger(DriverProducer.class.getName());

    @Inject
    private ConfigurationReader configurationReader;


    @ConfigurationUnit
    @Produces
    Driver getDriver(InjectionPoint injectionPoint) {
        Annotated annotated = injectionPoint.getAnnotated();
        ConfigurationUnit annotation = annotated.getAnnotation(ConfigurationUnit.class);
        LOGGER.info(String.format("Loading driver configuration from: %s the file %s",
                annotation.name(), annotation.fileName()));

        Settings settings = getSettings(annotation);

        String url = settings.getOrDefault("url", "bolt://localhost:7687").toString();
        String user = settings.getOrDefault("admin", "neo4j").toString();
        String password = settings.getOrDefault("password", "admin").toString();

        AuthToken basic = AuthTokens.basic(user, password);
        Driver driver = driver(url, basic);


        return driver;
    }

    private Settings getSettings(ConfigurationUnit annotation) {
        try {
            ConfigurationSettingsUnit unit = configurationReader.read(annotation);
            return unit.getSettings();
        } catch (ConfigurationException exp) {
            LOGGER.log(Level.WARNING, "An error to load configuration", exp);
            return Settings.of();
        }

    }
}