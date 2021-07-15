package com.pexapark.ElectricityFarm.model;

import com.pexapark.ElectricityFarm.util.DateTimeConverter;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TimeRangeRequestDto {
  @ApiModelProperty(
      value = "The first timestamp point from which capacity will be calculated",
      required = true,
      example = "2015-09-13 23:29:52.123")
  private LocalDateTime from;

  @ApiModelProperty(
      value = "The last timestamp point from which capacity will be calculated",
      required = true,
      example = "2016-09-13 23:29:52.123")
  private LocalDateTime to;

  public TimeRange convertToTimeRange() {
    return new TimeRange(this.getFrom(), this.getTo());
  }
}
