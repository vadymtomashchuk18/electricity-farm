package com.pexapark.ElectricityFarm.controllers;

import com.pexapark.ElectricityFarm.model.CapacityResultDto;
import com.pexapark.ElectricityFarm.model.RequestDto;
import com.pexapark.ElectricityFarm.services.CapacityFactorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(
    value = "Electricity Farm API",
    tags = {"API endpoints"})
@RequestMapping(value = "/api/v1")
@RestController
@Slf4j
@RequiredArgsConstructor
public class CapacityFactorController {

  private final CapacityFactorService capacityFactorService;

  @GetMapping(value = "/health")
  @ResponseStatus(HttpStatus.OK)
  public void healthCheck() {}

  @ApiOperation(value = "Calculate average capacity factor in time ranges")
  @GetMapping(value = "/capacityBetweenTwoTimestamps", produces = "application/json")
  public ResponseEntity<CapacityResultDto> getAverageCapacityBetweenTimeRange(
      @Valid @RequestBody RequestDto requestDto) {
    //    validate request
    //    Create B2B service
    return ResponseEntity.ok()
        .body(
            new CapacityResultDto(
                requestDto.getTimeRangeRequestDto().getFrom(),
                requestDto.getTimeRangeRequestDto().getTo(),
                capacityFactorService.calcCapacityFactorByTimeRange(
                    requestDto.getTimeRangeRequestDto().convertToTimeRange())));
  }
}
