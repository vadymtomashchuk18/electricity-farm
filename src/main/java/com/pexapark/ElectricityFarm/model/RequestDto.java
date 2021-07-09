package com.pexapark.ElectricityFarm.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestDto {

  @NotNull(message = "Electricity farm id is required")
  @ApiModelProperty(value = "Electricity farm id", required = true)
  private Long electricityFarmId;

  @NotNull(message = "Time range is required")
  @ApiModelProperty(value = "Time range", required = true)
  private TimeRangeRequestDto timeRangeRequestDto;

  //  @NotNull(message = "List of time ranges is required")
  //  @ApiModelProperty(value = "List of time ranges", required = true)
  //  private List<TimeRange> timeRangeList;
}
