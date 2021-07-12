package com.pexapark.ElectricityFarm.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CapacityResultByTimeRangesListDto {

    @ApiModelProperty(value = "Capacity results")
    @JsonProperty(value = "capacity_result")
    private List<CapacityResultDto> capacityResultDtoList;
}
