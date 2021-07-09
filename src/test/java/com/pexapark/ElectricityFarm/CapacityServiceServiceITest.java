package com.pexapark.ElectricityFarm;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import com.pexapark.ElectricityFarm.config.IntegrationTestDB;
import com.pexapark.ElectricityFarm.config.test.CleanDatabaseBeforeAndAfter;
import com.pexapark.ElectricityFarm.model.TimeRange;
import com.pexapark.ElectricityFarm.services.CapacityFactorService;
import org.assertj.core.api.Assertions;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.IfProfileValue;

import java.time.LocalDateTime;

@IfProfileValue(name = "spring.profiles.active", value = "integration")
@DBRider
@DBUnit(leakHunter = true, mergeDataSets = true)
public class CapacityServiceServiceITest extends IntegrationTestDB {

  @Autowired CapacityFactorService capacityFactorService;

 /** TODO: When DBrider startup will be cofigured to start after db initialization:
   *  1. Avoid Liquibase running for tests [-Dliquibase.should.run=false] or turnoff it in config file
   *  2. Use @CleanDatabaseBeforeAndAfter annotation for setup own data for each test
   *        Example: @CleanDatabaseBeforeAndAfter(value = "dbrider_data/expextedLiquibase_intialCapacityService.json")
   **/
  @Test
  @ExpectedDataSet(value = "dbrider_data/expextedLiquibase_intialCapacityService.json")
  public void test_testing() {
    TimeRange timeRange =
        new TimeRange(
            LocalDateTime.parse("2021-07-05T14:00:00.000000"),
            LocalDateTime.parse("2021-07-05T19:00:00.000000"));

    Assertions.assertThat(capacityFactorService.calcCapacityFactorByTimeRange(timeRange))
        .isEqualTo(6.17);
  }
}