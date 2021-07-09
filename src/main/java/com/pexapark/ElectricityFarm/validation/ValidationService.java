package com.pexapark.ElectricityFarm.validation;

import com.pexapark.ElectricityFarm.exception.ValidationException;
import com.pexapark.ElectricityFarm.model.TimeRange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class ValidationService {

  public void validateTimeRange(TimeRange timeRange) {
    log.info("Time rage validation started");
    if (Objects.isNull(timeRange)) {
      throw new ValidationException("Time range should be provided");
    }
    if (Objects.isNull(timeRange.getFrom()) || Objects.isNull(timeRange.getTo())) {
      throw new ValidationException("Any of timestamps should not be null");
    }
    if (timeRange.getFrom().equals(timeRange.getTo())) {
      throw new ValidationException(
          "Timestamps should not be equal. This range is incorrect: {} -> {}",
          timeRange.getFrom(),
          timeRange.getTo());
    }
    if (timeRange.getFrom().isAfter(timeRange.getTo())) {
      throw new ValidationException(
          "Time range should be provided from earlier to later. This range is incorrect: {} -> {}",
          timeRange.getFrom(),
          timeRange.getTo());
    }
    log.info("Time rage provided successfully");
  }
}
