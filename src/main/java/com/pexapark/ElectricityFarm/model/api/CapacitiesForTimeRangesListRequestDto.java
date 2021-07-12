package com.pexapark.ElectricityFarm.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pexapark.ElectricityFarm.model.TimeRangeRequestDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CapacitiesForTimeRangesListRequestDto {

  @ApiModelProperty(value = "Electricity farm id", required = true)
  @JsonProperty(value = "electricity_farm_id")
  private Long electricityFarmId;

  @ApiModelProperty(value = "List of time ranges. For each of them capacity will be calculated", required = true)
  @JsonProperty(value = "time_ranges")
  private List<TimeRangeRequestDto> timeRangeRequestDtoList;
}
