package com.assessment.payment.util;

import com.assessment.payment.entity.*;
import com.assessment.payment.request.PaymentRequest;

import java.util.UUID;
/**
 * Created by Aaima Bashir on 1/26/2022
 */
public class PaymentDataProvider {
  public PaymentRequest getValidPaymentRequest() {
    return PaymentRequest.builder()
        .amount(100.0)
        .currency("USD")
        .chargeId("123")
        .customer(getCustomer())
        .fee(getFee())
        .build();
  }

  public PaymentRequest getBadPaymentRequest() {
    return PaymentRequest.builder()
        .amount(100.0)
        .currency("1")
        .chargeId("123")
        .customer(getCustomer())
        .fee(getFee())
        .build();
  }

  public Customer getCustomer() {
    return Customer.builder().id(1).name("Aaima Bashir").walletId(1).build();
  }

  public Wallet getWallet() {
    return Wallet.builder().id(1).balance(getBalance()).currency("USD").build();
  }

  public Balance getBalance() {
    Balance balance = new Balance();
    balance.setId(1);
    balance.setTotalAmount(100.0);
    return balance;
  }

  public Payment getPayment() {
    return Payment.builder()
        .id(UUID.randomUUID())
        .amount(100.0)
        .status("SUCCESS")
        .chargeId("123")
        .currency("USD")
        .fee(getFee())
        .customer(getCustomer())
        .build();
  }

  public Fee getFee() {
    return Fee.builder().currency("USD").amount(1.0).build();
  }
}
