package com.pexapark.ElectricityFarm.exception;

import com.pexapark.ElectricityFarm.exception.error_codes.CalculationErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
public class ApiValidationException extends ValidationException {

    private final CalculationErrorCode validationErrorCode;

    public ApiValidationException(CalculationErrorCode validationErrorCode, String message, Object... args) {
        super(message, args);
        this.validationErrorCode = validationErrorCode;
    }
}
