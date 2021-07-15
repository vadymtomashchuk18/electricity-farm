package com.pexapark.ElectricityFarm.services;

import com.pexapark.ElectricityFarm.exception.ApiValidationException;
import com.pexapark.ElectricityFarm.exception.ValidationException;
import com.pexapark.ElectricityFarm.exception.error_codes.CalculationErrorCode;
import com.pexapark.ElectricityFarm.model.TimeRangeRequestDto;
import com.pexapark.ElectricityFarm.model.api.CapacitiesForTimeRangesListRequestDto;
import com.pexapark.ElectricityFarm.model.api.CapacityInTimeRangeRequestDto;
import com.pexapark.ElectricityFarm.model.api.CapacityResultByTimeRangesListDto;
import com.pexapark.ElectricityFarm.model.api.CapacityResultDto;
import com.pexapark.ElectricityFarm.validation.ValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class B2BFarmCapacityFactorService {

  private final CapacityFactorService capacityFactorService;
  private final ValidationService validationService;

  public CapacityResultDto processCapacityInTimeRangeRequest(
      CapacityInTimeRangeRequestDto inTimeRangeRequestDto) {
    return getCapacityResultDto(
        inTimeRangeRequestDto.getElectricityFarmId(),
        inTimeRangeRequestDto.getTimeRangeRequestDto());
  }

  public CapacityResultByTimeRangesListDto processCapacitiesInTimeRangesListRequest(
      CapacitiesForTimeRangesListRequestDto forTimeRangesListRequestDto) {
    List<CapacityResultDto> capacityResultDtos =
        forTimeRangesListRequestDto.getTimeRangeRequestDtoList().stream()
            .map(
                timeRangeRequestDto ->
                    getCapacityResultDto(
                        forTimeRangesListRequestDto.getElectricityFarmId(), timeRangeRequestDto))
            .collect(Collectors.toList());
    return new CapacityResultByTimeRangesListDto(capacityResultDtos);
  }

  private CapacityResultDto getCapacityResultDto(
      Long electricityFarmId, TimeRangeRequestDto requestDto) {
    try {
      validationService.validateTimeRange(requestDto.convertToTimeRange());
    } catch (ValidationException validationException) {
      throw new ApiValidationException(
          CalculationErrorCode.WRONG_TIME_RANGE, validationException.getMessage());
    }
    return new CapacityResultDto(
        electricityFarmId,
        requestDto.getFrom(),
        requestDto.getTo(),
        capacityFactorService.capacityFactorInTimeRange(
            electricityFarmId, requestDto.convertToTimeRange()));
  }
}
