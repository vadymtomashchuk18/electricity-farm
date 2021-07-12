package com.pexapark.ElectricityFarm.controllers;

import com.pexapark.ElectricityFarm.model.api.CapacitiesForTimeRangesListRequestDto;
import com.pexapark.ElectricityFarm.model.api.CapacityInTimeRangeRequestDto;
import com.pexapark.ElectricityFarm.model.api.CapacityResultByTimeRangesListDto;
import com.pexapark.ElectricityFarm.model.api.CapacityResultDto;
import com.pexapark.ElectricityFarm.services.B2BFarmCapacityFactorService;
import com.pexapark.ElectricityFarm.services.CapacityFactorService;
import com.pexapark.ElectricityFarm.validation.ValidationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
public class B2BFarmCapacityFactorApi {

  private final CapacityFactorService capacityFactorService;
  private final ValidationService validationService;

  private final B2BFarmCapacityFactorService b2BFarmCapacityFactorService;

  @GetMapping(value = "/health")
  @ResponseStatus(HttpStatus.OK)
  public void healthCheck() {}

  @ApiOperation(
      value = "Calculate capacity factor in time range",
      notes =
          "The API endpoint is used to calculate capacity factor for one electricity farm by a single time range. Time range should contain 'from' and 'to' timestamps (check the TimeRangeRequestDto description).")
  @ApiResponses(
      value = {
        @ApiResponse(
            code = 200,
            message = "Data is loaded and calculated successfully",
            response = CapacityResultDto.class),
        @ApiResponse(
            code = 400,
            message =
                "Calculation failed because of not valid data:\n"
                    + "\n"
                    + "## Error codes\n"
                    + "\n"
                    + "| Code | Description |\n"
                    + "|---|---|\n"
                    + "| WRONG_TIME_RANGE | Time range is specified incorrectly. |\n"
                    + "| WRONG_REQUEST_DATA | Request validation is failed. |\n")
      })
  @PostMapping(value = "/capacityInTimeRange", produces = "application/json")
  public ResponseEntity<?> getCapacityInTimeRange(
      @Valid @RequestBody CapacityInTimeRangeRequestDto inTimeRangeRequestDto) {
    validationService.validateCapacityInTimeRangeRequestData(inTimeRangeRequestDto);
    return ResponseEntity.ok()
        .body(
            b2BFarmCapacityFactorService.processCapacityInTimeRangeRequest(inTimeRangeRequestDto));
  }

  @ApiOperation(
      value =
          "The API endpoint is used to calculate capacity factor for one electricity farm by a each of the provided time ranges")
  @ApiResponses(
      value = {
        @ApiResponse(
            code = 200,
            message = "Data is loaded and calculated successfully",
            response = CapacityResultByTimeRangesListDto.class),
        @ApiResponse(
            code = 400,
            message =
                "Calculation failed because of not valid data:\n"
                    + "\n"
                    + "## Error codes\n"
                    + "\n"
                    + "| Code | Description |\n"
                    + "|---|---|\n"
                    + "| WRONG_TIME_RANGE | Time range is specified incorrectly. |\n"
                    + "| WRONG_REQUEST_DATA | Request validation is failed. |\n")
      })
  @PostMapping(value = "/capacitiesForTimeRangesList", produces = "application/json")
  public ResponseEntity<?> getAverageCapacitiesForTimeRangesList(
      @Valid @RequestBody CapacitiesForTimeRangesListRequestDto forTimeRangesListRequestDto) {
    validationService.validateCapacitiesForTimeRangesListRequestData(forTimeRangesListRequestDto);
    return ResponseEntity.ok()
        .body(
            b2BFarmCapacityFactorService.processCapacitiesInTimeRangesListRequest(
                forTimeRangesListRequestDto));
  }
}
