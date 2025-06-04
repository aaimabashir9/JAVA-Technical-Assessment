package com.assessment.payment.service;

import com.assessment.payment.entity.Balance;
import com.assessment.payment.entity.Payment;
import com.assessment.payment.repository.BalanceRepository;
import com.assessment.payment.repository.FeeRepository;
import com.assessment.payment.repository.PaymentRepository;
import com.assessment.payment.repository.WalletRepository;
import com.assessment.payment.request.PaymentRequest;
import com.assessment.payment.response.PaymentResponse;
import com.assessment.payment.util.PaymentDataProvider;
import com.assessment.payment.validator.PaymentValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doReturn;

/**
 * Created by Aaima Bashir on 1/26/2022
 */
public class PaymentServiceTest {

  @InjectMocks private PaymentService paymentService;

  private PaymentDataProvider paymentDataProvider;

  @Mock private PaymentValidator paymentValidator;

  @Mock private WalletRepository walletRepository;

  @Mock private FeeRepository feeRepository;

  @Mock private BalanceRepository balanceRepository;

  @Mock private PaymentRepository paymentRepository;

  @BeforeEach
  void initUseCase() {
    MockitoAnnotations.openMocks(this);
    paymentDataProvider = new PaymentDataProvider();
  }

  @Test
  public void topUpWallet_success() {

    doReturn(Collections.EMPTY_LIST)
        .when(paymentValidator)
        .validatePaymentRequest(any(PaymentRequest.class));

    doReturn(Optional.ofNullable(paymentDataProvider.getWallet()))
        .when(walletRepository)
        .findById(any(Integer.class));

    doReturn(Optional.ofNullable(paymentDataProvider.getFee()))
        .when(feeRepository)
        .findFirstByAmountAndCurrency(any(Double.class), any(String.class));

    doReturn(Optional.ofNullable(paymentDataProvider.getBalance()))
        .when(balanceRepository)
        .findById(anyInt());

    doReturn(paymentDataProvider.getBalance()).when(balanceRepository).save(any(Balance.class));

    doReturn(paymentDataProvider.getPayment()).when(paymentRepository).save(any(Payment.class));

    ResponseEntity<PaymentResponse> paymentResponseEntity =
        paymentService.topUpWallet(paymentDataProvider.getValidPaymentRequest());
    assertNotNull(paymentResponseEntity);
    assertEquals(HttpStatus.OK, paymentResponseEntity.getStatusCode());
  }
}
