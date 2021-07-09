package com.pexapark.ElectricityFarm.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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

/*  @ApiModelProperty(
      value = "Time range between which capacity is calculated",
      example = "{from: 2019-11-12T13:28:22, to: 2019-12-12T13:28:22}")
  private TimeRange timeRange;*/

  @ApiModelProperty(
          value = "Time from which capacity will be calculated",
          example = "from: 2019-11-12T13:28:22")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime from;

  @ApiModelProperty(
          value = "Time untill which capacity will be calculated",
          example = "from: 2019-11-12T13:28:22")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime to;

  @ApiModelProperty(value = "Calculated capacity value", example = "8.906")
  private Double capacityValue;
}
