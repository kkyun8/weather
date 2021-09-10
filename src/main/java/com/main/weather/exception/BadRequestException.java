package com.main.weather.exception;

public class BadRequestException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private String errorCode;

  public BadRequestException(String code) {
    this.errorCode = code;
  }

  public String getErrorCode() {
    return errorCode;
  }
}
