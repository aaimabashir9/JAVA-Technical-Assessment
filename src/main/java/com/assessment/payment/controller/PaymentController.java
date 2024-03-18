package com.assessment.payment.controller;

import com.assessment.payment.entity.Payment;
import com.assessment.payment.request.PaymentRequest;
import com.assessment.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

/**
 * Created by Aaima Bashir on 1/24/2022
 */

@RestController
@Validated
@RequestMapping(value = "/payment")
public class PaymentController {

  @Autowired private PaymentService paymentService;

  @PostMapping(
      value = "/topupwallet",
      consumes = {MediaType.APPLICATION_JSON_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<Payment> topUpWallet(@Valid @RequestBody PaymentRequest paymentRequest) {
    return paymentService.topUpWallet(paymentRequest);
  }
}
