package com.pexapark.ElectricityFarm.services;

import com.pexapark.ElectricityFarm.model.ElectricityFarm;
import com.pexapark.ElectricityFarm.model.HourlyProductionData;
import com.pexapark.ElectricityFarm.model.TimeRange;
import com.pexapark.ElectricityFarm.validation.ValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public class CapacityFactorService {
  private final HourlyProductionDataService hourlyProductionDataService;
  private final ElectricityFarmService electricityFarmService;
  private final ValidationService validationService;

  @Autowired
  public CapacityFactorService(
      HourlyProductionDataService hourlyProductionDataService,
      ElectricityFarmService electricityFarmService,
      ValidationService validationService) {
    this.hourlyProductionDataService = hourlyProductionDataService;
    this.electricityFarmService = electricityFarmService;
    this.validationService = validationService;
  }

  public BigDecimal capacityFactorInTimeRange(Long electricityFarmId, TimeRange timeRange) {
    ElectricityFarm currentElectricityFarm =
        electricityFarmService.getDefaultElectricityFarm(electricityFarmId);
    TimeRange timeRangeInUTC =
        timeRange.convertToTimeRangeInUTC(currentElectricityFarm.getTimeZone());

    List<HourlyProductionData> productionDataList =
        hourlyProductionDataService.getProductionDataListInTimeRange(
            currentElectricityFarm, timeRangeInUTC);

    if (productionDataList.isEmpty()) {
      return BigDecimal.ZERO;
    }

    productionDataList.sort(Comparator.comparing(HourlyProductionData::getTimestamp));

    int maxNumberOfProductionData =
        maxNumberOfDataInTimeRange(timeRangeInUTC, productionDataList.get(0).getTimestamp());

    BigDecimal maxPossibleCapacityInTimeRange =
        currentElectricityFarm
            .getFarmCapacityMW()
            .multiply(BigDecimal.valueOf(maxNumberOfProductionData));
    BigDecimal realCapacityInTimeRange =
        productionDataList.stream()
            .map(HourlyProductionData::getElectricityProducedMWh)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

    return realCapacityInTimeRange
        //        .multiply(BigDecimal.valueOf(100))
        .divide(maxPossibleCapacityInTimeRange, 10, RoundingMode.HALF_UP);
  }

  //  As here we have time points in UTC there are no needs to check the time shifting
  //  3600 seconds - ideal step between production data
  private int maxNumberOfDataInTimeRange(TimeRange timeRange, LocalDateTime firstTimePoint) {
    long secondsToFirstTimePoint = ChronoUnit.SECONDS.between(timeRange.getFrom(), firstTimePoint);
    long secondsOfTimeRange = ChronoUnit.SECONDS.between(timeRange.getFrom(), timeRange.getTo());
    if (secondsToFirstTimePoint == 0) {
      return Math.toIntExact(1 + secondsOfTimeRange / 3600);
    } else {
      return Math.toIntExact(secondsOfTimeRange / 3600);
    }
  }
}
