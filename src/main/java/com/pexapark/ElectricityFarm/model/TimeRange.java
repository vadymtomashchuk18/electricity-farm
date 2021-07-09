package com.pexapark.ElectricityFarm.model;

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
}
