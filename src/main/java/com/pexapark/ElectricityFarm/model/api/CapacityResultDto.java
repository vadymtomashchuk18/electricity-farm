package com.pexapark.ElectricityFarm.model.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CapacityResultDto {

  @ApiModelProperty(value = "Electricity farm id", example = "321")
  @JsonProperty(value = "electricity_farm_id")
  private Long electricityFarmId;

  @ApiModelProperty(
      value = "The first timestamp point from which capacity will be calculated",
      example = "2019-11-12 13:28:22")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime from;

  @ApiModelProperty(
      value = "The last timestamp point from which capacity will be calculated",
      example = "2020-11-12 13:28:22")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime to;

  @ApiModelProperty(value = "Calculated capacity value", example = "8.906")
  @JsonProperty(value = "capacity_value")
  private Double capacityValue;
}
