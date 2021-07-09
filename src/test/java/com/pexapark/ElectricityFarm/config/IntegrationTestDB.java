package com.pexapark.ElectricityFarm.config;

import com.pexapark.ElectricityFarm.config.test.IntegrationTestPostgresContainer;
import org.junit.BeforeClass;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest
@ContextConfiguration(initializers = {IntegrationTestDB.Initializer.class})
public class IntegrationTestDB {

  private static final PostgreSQLContainer sqlContainer;

  static {
    sqlContainer = IntegrationTestPostgresContainer.getInstance();
    sqlContainer.start();
  }

  static class Initializer
      implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
      TestPropertyValues.of(
              "spring.datasource.url=" + sqlContainer.getJdbcUrl(),
              "spring.datasource.username=" + sqlContainer.getUsername(),
              "spring.datasource.password=" + sqlContainer.getPassword())
          .applyTo(configurableApplicationContext.getEnvironment());
    }
  }
}
