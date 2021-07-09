package com.pexapark.ElectricityFarm.config.test;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;

public class IntegrationTestPostgresContainer
    extends PostgreSQLContainer<IntegrationTestPostgresContainer> {

  private static final String IMAGE_VERSION = "postgres:11";
  private static IntegrationTestPostgresContainer container;

  private IntegrationTestPostgresContainer() {
    super(IMAGE_VERSION);
  }

  public static IntegrationTestPostgresContainer getInstance() {
    if (container == null) {
      container =
          new IntegrationTestPostgresContainer()
              .withDatabaseName("test_electricity_farm")
              .withUsername("test_electricity_farm")
              .withPassword("test_electricity_farm")
              .waitingFor(Wait.forListeningPort());
      ;
    }
    return container;
  }

  @Override
  public void start() {
    super.start();
    System.setProperty("DB_URL", container.getJdbcUrl());
    System.setProperty("DB_USERNAME", container.getUsername());
    System.setProperty("DB_PASSWORD", container.getPassword());
  }

  @Override
  public void stop() {
    // do nothing, JVM handles shut down
  }
}
