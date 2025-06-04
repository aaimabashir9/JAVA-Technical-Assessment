package com.assessment.payment.exception;

// import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
// import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// import java.util.stream.Collectors;
/**
 * Created by Aaima Bashir on 1/26/2022
 */
@ControllerAdvice
public class PaymentExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(PaymentException.class)
  public ResponseEntity<?> handleCustomException(PaymentException ex, WebRequest request) {
    List<String> errors = new ArrayList<>();

    errors.add(ex.getMessage());
    Map<String, List<String>> result = new HashMap<>();
    result.put("errors", errors);

    return new ResponseEntity<>(result, ex.getHttpStatus());
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex) {
    List<String> errors = new ArrayList<>();

    for (ConstraintViolation<?> constraint : ex.getConstraintViolations()) {
      errors.add(
          constraint.getRootBeanClass().getSimpleName()
              + " ["
              + constraint.getMessageTemplate()
              + "]");
    }
    Map<String, List<String>> result = new HashMap<>();
    result.put("errors", errors);

    return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
  }

  // @ExceptionHandler(MethodArgumentNotValidException.class)
  // protected ResponseEntity<?> handleMethodArgumentNotValid(
  //     MethodArgumentNotValidException ex,
  //     HttpHeaders headers,
  //     HttpStatus status,
  //     WebRequest request) {

  //   Map<String, Object> body = new LinkedHashMap<>();
  //   body.put("timestamp", LocalDate.now());
  //   body.put("status", status.value());

  //   List<String> errors =
  //       ex.getBindingResult().getFieldErrors().stream()
  //           .map(x -> x.getDefaultMessage())
  //           .collect(Collectors.toList());

  //   body.put("errors", errors);
  //   return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  // }
}
