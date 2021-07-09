package com.pexapark.ElectricityFarm.exception;

import org.slf4j.helpers.MessageFormatter;

public class ValidationException extends RuntimeException {

  public ValidationException(String message, Object... args) {
    super(MessageFormatter.arrayFormat(message, args).getMessage());
  }
}
