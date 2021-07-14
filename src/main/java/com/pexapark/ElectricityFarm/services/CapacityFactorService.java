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

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
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

  public BigDecimal calcCapacityFactorByTimeRange(
      @NotNull Long electricityFarmId, TimeRange timeRange) {
    ElectricityFarm currentElectricityFarm =
        electricityFarmService.getDefaultElectricityFarm(electricityFarmId);
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

  private BigDecimal calcAverageCapacityOfProducedElectricity(
      List<HourlyProductionData> productionDataList) {
    BigDecimal sum =
        productionDataList.stream()
            .map(HourlyProductionData::getElectricityProducedMWh)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    return productionDataList.size() == 0
        ? BigDecimal.ZERO
        : sum.divide(BigDecimal.valueOf(productionDataList.size()),RoundingMode.HALF_UP);
  }
}
