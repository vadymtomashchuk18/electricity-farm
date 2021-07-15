package com.pexapark.ElectricityFarm.services;

import com.pexapark.ElectricityFarm.model.ElectricityFarm;
import com.pexapark.ElectricityFarm.model.HourlyProductionData;
import com.pexapark.ElectricityFarm.model.TimeRange;
import com.pexapark.ElectricityFarm.repository.HourlyProductionDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HourlyProductionDataService {

  private final HourlyProductionDataRepository hourlyProductionDataRepository;

  public HourlyProductionDataService(
      HourlyProductionDataRepository hourlyProductionDataRepository) {
    this.hourlyProductionDataRepository = hourlyProductionDataRepository;
  }

  public List<HourlyProductionData> getProductionDataListInTimeRange(
      ElectricityFarm currentElectricityFarm, TimeRange timeRange) {

    return hourlyProductionDataRepository
        .findHourlyProductionDataByElectricityFarmAndTimestampBetween(
            currentElectricityFarm, timeRange.getFrom(), timeRange.getTo());
  }
}
