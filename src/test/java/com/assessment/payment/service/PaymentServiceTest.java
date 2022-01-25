package com.assessment.payment.service;

import com.assessment.payment.PaymentApplication;
import com.assessment.payment.entity.*;
import com.assessment.payment.repository.BalanceRepository;
import com.assessment.payment.repository.FeeRepository;
import com.assessment.payment.repository.PaymentRepository;
import com.assessment.payment.repository.WalletRepository;
import com.assessment.payment.request.PaymentRequest;
import com.assessment.payment.validator.PaymentValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PaymentApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PaymentServiceTest {
  @Mock private PaymentRepository paymentRepository;

  @Mock private FeeRepository feeRepository;

  @Mock private WalletRepository walletRepository;

  @Mock private BalanceRepository balanceRepository;

  @Mock private PaymentValidator paymentValidator;

  @InjectMocks private PaymentService paymentService;

  @Before
  public void setUp() {
    initMocks(this);
  }

  @Test
  public void topUpWallet_success() {
    Mockito.when(paymentValidator.validatePaymentRequest(any(PaymentRequest.class)))
        .thenReturn(Collections.EMPTY_LIST);

    Mockito.when(walletRepository.findById(any(Integer.class)))
        .thenReturn(Optional.ofNullable(getWallet()));

    ResponseEntity<Payment> paymentResponseEntity =
        paymentService.topUpWallet(getValidPaymentRequest());
    assertNotNull(paymentResponseEntity);
  }

  private PaymentRequest getValidPaymentRequest() {
    return PaymentRequest.builder()
        .amount(100.0)
        .currency("USD")
        .chargeId("123")
        .customer(getCustomer())
        .fee(getFee())
        .build();
  }

  private Customer getCustomer() {
    return Customer.builder().id(1).name("Aaima Bashir").walletId(1).build();
  }

  private Wallet getWallet() {
    return Wallet.builder().id(1).balance(getBalance()).currency("USD").build();
  }

  private Balance getBalance() {
    Balance balance = new Balance();
    balance.setId(1);
    balance.setTotalAmount(100.0);
    return balance;
  }

  private Fee getFee() {
    return Fee.builder().currency("USD").amount(1.0).build();
  }
}
