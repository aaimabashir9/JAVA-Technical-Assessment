package com.assessment.payment.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Created by Aaima Bashir on 1/26/2022
 */

@Getter
public class PaymentException extends RuntimeException {
  private HttpStatus httpStatus;

  public PaymentException(String message, HttpStatus httpStatus) {
    super(message);
    this.httpStatus = httpStatus;
  }

  public PaymentException(String message, Throwable cause, HttpStatus httpStatus) {
    super(message, cause);
    this.httpStatus = httpStatus;
  }
}
