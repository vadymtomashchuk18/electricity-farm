package com.pexapark.ElectricityFarm.model;

import com.pexapark.ElectricityFarm.util.DateTimeConverter;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TimeRange {

  private LocalDateTime from;
  private LocalDateTime to;

  public TimeRange convertToTimeRangeInUTC(String timeZone) {
    LocalDateTime from = DateTimeConverter.localDateTimeWithTimezoneToUTC(this.getFrom(), timeZone);
    LocalDateTime to = DateTimeConverter.localDateTimeWithTimezoneToUTC(this.getTo(), timeZone);
    return new TimeRange(from, to);
  }
}
