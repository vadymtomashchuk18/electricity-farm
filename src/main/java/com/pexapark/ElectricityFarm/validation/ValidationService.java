package com.pexapark.ElectricityFarm.validation;

import com.pexapark.ElectricityFarm.exception.ApiValidationException;
import com.pexapark.ElectricityFarm.exception.ValidationException;
import com.pexapark.ElectricityFarm.exception.error_codes.CalculationErrorCode;
import com.pexapark.ElectricityFarm.model.TimeRange;
import com.pexapark.ElectricityFarm.model.api.CapacitiesForTimeRangesListRequestDto;
import com.pexapark.ElectricityFarm.model.api.CapacityInTimeRangeRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class ValidationService {

  public void validateTimeRange(TimeRange timeRange) {
    log.info("Time rage validation started");
    if (Objects.isNull(timeRange.getFrom()) || Objects.isNull(timeRange.getTo())) {
      throw new ValidationException("Any of timestamps should not be null");
    } else if (timeRange.getFrom().equals(timeRange.getTo())) {
      throw new ValidationException(
          "Timestamps should not be equal. This range is incorrect: {} -> {}",
          timeRange.getFrom(),
          timeRange.getTo());
    } else if (timeRange.getFrom().isAfter(timeRange.getTo())) {
      throw new ValidationException(
          "Time range should be provided from earlier to later. This range is incorrect: {} -> {}",
          timeRange.getFrom(),
          timeRange.getTo());
    }
    log.info("Time rage provided successfully");
  }

  public void validateCapacityInTimeRangeRequestData(
      CapacityInTimeRangeRequestDto inTimeRangeRequestDto) {
    log.info("Capacity in time range request validation");
    if (Objects.isNull(inTimeRangeRequestDto)) {
      throw new ApiValidationException(
          CalculationErrorCode.WRONG_REQUEST_DATA, "Request dto should not be null");
    } else if (Objects.isNull(inTimeRangeRequestDto.getElectricityFarmId())) {
      throw new ApiValidationException(
          CalculationErrorCode.WRONG_REQUEST_DATA, "Electricity farm id should not be null");
    } else if (Objects.isNull(inTimeRangeRequestDto.getTimeRangeRequestDto())) {
      throw new ApiValidationException(
          CalculationErrorCode.WRONG_TIME_RANGE, "Time range dto should not be null");
    }
  }

  public void validateCapacitiesForTimeRangesListRequestData(
      CapacitiesForTimeRangesListRequestDto forTimeRangesListRequestDto) {
    log.info("Capacities for time ranges request validation");
    if (Objects.isNull(forTimeRangesListRequestDto)) {
      throw new ApiValidationException(
          CalculationErrorCode.WRONG_REQUEST_DATA, "Request dto should not be null");
    } else if (Objects.isNull(forTimeRangesListRequestDto.getElectricityFarmId())) {
      throw new ApiValidationException(
          CalculationErrorCode.WRONG_REQUEST_DATA, "Electricity farm id should not be null");
    } else if (CollectionUtils.isEmpty(forTimeRangesListRequestDto.getTimeRangeRequestDtoList())) {
      throw new ApiValidationException(
          CalculationErrorCode.WRONG_REQUEST_DATA,
          "Time ranges list should contain at least one element");
    }
  }
}
