package com.assessment.payment.validator;

import com.assessment.payment.repository.CustomerRepository;
import com.assessment.payment.repository.WalletRepository;
import com.assessment.payment.request.PaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PaymentValidator {
  @Autowired private CustomerRepository customerRepository;
  @Autowired private WalletRepository walletRepository;

  public List<String> validatePaymentRequest(final PaymentRequest paymentRequest) {
    List<String> errors = new ArrayList<>();
    errors.addAll(validateCustomerData(paymentRequest));
    errors.addAll(validateCurrency(paymentRequest));
    // validate if previous charge is found against charge_id
    return errors;
  }

  private List<String> validateCurrency(PaymentRequest paymentRequest) {
    List<String> errors = new ArrayList<>();
    String topUpCurrency = paymentRequest.getCurrency();
    String feeCurrency = paymentRequest.getFee().getCurrency();
    if (!topUpCurrency.equalsIgnoreCase(feeCurrency)) {
      errors.add("Request parameters[currency] and [fees.currency] does not match.");
    }
    try {
      Currency.getInstance(topUpCurrency);
      Currency.getInstance(feeCurrency);
    } catch (IllegalArgumentException ex) {
      errors.add("Request parameter[currency/fees.currency] is not valid.");
    }
    return errors;
  }

  private List<String> validateCustomerData(PaymentRequest paymentRequest) {
    List<String> errors = new ArrayList<>();
    if (customerRepository.findById(paymentRequest.getCustomer().getId()).isEmpty()) {
      errors.add("Customer does not exist for request parameter[customer.id].");
    }
    if (walletRepository.findById(paymentRequest.getCustomer().getWalletId()).isEmpty()) {
      errors.add("Wallet does not exist for request parameter[customer.wallet_id].");
    }
    return errors;
  }
}
