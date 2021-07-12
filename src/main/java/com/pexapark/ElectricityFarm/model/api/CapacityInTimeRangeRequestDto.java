package com.pexapark.ElectricityFarm.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pexapark.ElectricityFarm.model.TimeRangeRequestDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CapacityInTimeRangeRequestDto {

  @ApiModelProperty(value = "Electricity farm id", required = true)
  @JsonProperty(value = "electricity_farm_id")
  private Long electricityFarmId;

  @ApiModelProperty(value = "Time range", required = true)
  @JsonProperty(value = "time_range")
  private TimeRangeRequestDto timeRangeRequestDto;

}
