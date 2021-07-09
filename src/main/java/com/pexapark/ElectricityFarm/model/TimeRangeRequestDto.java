package com.pexapark.ElectricityFarm.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class TimeRangeRequestDto {
  @NotNull(message = "Trade date is required")
  @ApiModelProperty(
      value = "Date at which the transaction was",
      required = true,
      example = "2015-09-13T23:29:52.123Z")
  private LocalDateTime from;

  @NotNull(message = "Trade date is required")
  @ApiModelProperty(
      value = "Date at which the transaction was",
      required = true,
      example = "2015-09-13T23:29:52.123Z")
  private LocalDateTime to;

  public TimeRange convertToTimeRange() {
    return new TimeRange(this.getFrom(), this.getTo());
  }
}
