package com.pexapark.ElectricityFarm.services;

import com.pexapark.ElectricityFarm.model.ElectricityFarm;
import com.pexapark.ElectricityFarm.model.HourlyProductionData;
import com.pexapark.ElectricityFarm.model.TimeRange;
import com.pexapark.ElectricityFarm.repository.HourlyProductionDataRepository;
import com.pexapark.ElectricityFarm.util.DateTimeConverter;
import com.pexapark.ElectricityFarm.validation.ValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class CapacityFactorService {
  private final HourlyProductionDataRepository hourlyProductionDataRepository;
  private final ElectricityFarmService electricityFarmService;
  private final ValidationService validationService;

  @Autowired
  public CapacityFactorService(
      HourlyProductionDataRepository hourlyProductionDataRepository,
      ElectricityFarmService electricityFarmService,
      ValidationService validationService) {
    this.hourlyProductionDataRepository = hourlyProductionDataRepository;
    this.electricityFarmService = electricityFarmService;
    this.validationService = validationService;
  }

  public double calcCapacityFactorByTimeRange(TimeRange timeRange) {
    validationService.validateTimeRange(timeRange);
    ElectricityFarm currentElectricityFarm = electricityFarmService.getDefaultElectricityFarm(1);
    log.info("Calculating according to {} time zone", currentElectricityFarm.getTimeZone());

    LocalDateTime from =
        DateTimeConverter.localDateTimeWithTimezoneToUTC(
            timeRange.getFrom(), currentElectricityFarm.getTimeZone());
    LocalDateTime to =
        DateTimeConverter.localDateTimeWithTimezoneToUTC(
            timeRange.getTo(), currentElectricityFarm.getTimeZone());

    List<HourlyProductionData> productionDataList =
        hourlyProductionDataRepository.findHourlyProductionDataByElectricityFarmAndTimestampBetween(
            currentElectricityFarm, from, to);

    return calcAverageCapacityOfProducedElectricity(productionDataList);
  }

  private double calcAverageCapacityOfProducedElectricity(
      List<HourlyProductionData> productionDataList) {
    return productionDataList.stream()
        .mapToDouble(HourlyProductionData::getElectricityProducedMWh)
        .summaryStatistics()
        .getAverage();
  }
}
