package com.pexapark.ElectricityFarm.exception;

import com.pexapark.ElectricityFarm.exception.error_codes.CalculationErrorCode;

public class ApiValidationException extends ValidationException {

    private final CalculationErrorCode validationErrorCode;

    public ApiValidationException(CalculationErrorCode validationErrorCode, String message, Object... args) {
        super(message, args);
        this.validationErrorCode = validationErrorCode;
    }
}
